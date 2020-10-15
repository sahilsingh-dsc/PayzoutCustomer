package com.payzout.customer.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.AuthInterface;
import com.payzout.customer.auth.model.CheckUser;
import com.payzout.customer.lending.kyc.KycOnBoardActivity;
import com.payzout.customer.modules.kyc.KycActivity;
import com.payzout.customer.modules.loan.PLActivity;
import com.payzout.customer.utils.Constant;

import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CodeActivity extends AppCompatActivity implements View.OnClickListener {

    private final static String country = "+91";
    private static final String TAG = "CodeActivity";
    private TextView tvCodeNotice;
    private FirebaseAuth firebaseAuth;
    private TextView tvContinue;
    private TextView tvCodeStatus;
    private TextView tvResendCode;
    private EditText etCode;
    private ImageView ivGoBack;
    private String phoneVerificationId;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            verificationCallbacks;
    private PhoneAuthProvider.ForceResendingToken resendToken;
    private LinearLayout lhAutoVerify;
    private FrameLayout frameCode;
    private String phoneNumber;

    private AuthInterface authInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_code);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        initView();
    }

    private void initView() {
        tvCodeNotice = findViewById(R.id.tvCodeNotice);
        firebaseAuth = FirebaseAuth.getInstance();
        lhAutoVerify = findViewById(R.id.lhAutoVerify);
        tvContinue = findViewById(R.id.tvContinue);
        etCode = findViewById(R.id.etCode);
        frameCode = findViewById(R.id.frameCode);
        tvCodeStatus = findViewById(R.id.tvCodeStatus);
        tvResendCode = findViewById(R.id.tvResendCode);
        ivGoBack = findViewById(R.id.ivGoBack);
        tvContinue.setOnClickListener(this);
        tvResendCode.setOnClickListener(this);
        ivGoBack.setOnClickListener(this);

        authInterface = APIClient.getRetrofitInstance().create(AuthInterface.class);
        phoneNumber = getIntent().getStringExtra("phoneNumber");

        setNotice();
        sendCode();
    }

    private void sendCode() {
        lhAutoVerify.setVisibility(View.VISIBLE);
        setUpVerificationCallbacks();
        tvCodeStatus.setText(Constant.AUTH_SENDING_CODE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(country + phoneNumber, 60, TimeUnit.SECONDS, this, verificationCallbacks);
    }

    private void setUpVerificationCallbacks() {
        Log.e(TAG, "setUpVerificationCallbacks: ");
        verificationCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                signInWithPhoneAuthCredential(phoneAuthCredential);
                Log.e(TAG, "onVerificationCompleted: ");
            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                Log.e(TAG, "onVerificationFailed: " + e);
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    Log.e(TAG, "onVerificationFailed: ");
                    Snackbar.make(frameCode, "Invalid Credentials", Snackbar.LENGTH_SHORT).show();
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    Log.e(TAG, "onVerificationFailed: ");
                    lhAutoVerify.setVisibility(View.GONE);
                    Snackbar.make(frameCode, "Limit Reached Try Again In a few Hours", Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                tvResendCode.setVisibility(View.VISIBLE);
                tvCodeStatus.setText(Constant.AUTH_AUTO_VERIFY);
                phoneVerificationId = s;
                resendToken = forceResendingToken;
                Log.e(TAG, "onCodeSent: ");
            }

            @Override
            public void onCodeAutoRetrievalTimeOut(@NonNull String s) {
                super.onCodeAutoRetrievalTimeOut(s);
                lhAutoVerify.setVisibility(View.GONE);
                Log.e(TAG, "onCodeAutoRetrievalTimeOut: ");
            }
        };
    }

    private void setNotice() {
        String codeNotice = getResources().getString(R.string.code_notice) + " " + phoneNumber + ".";
        tvCodeNotice.setText(codeNotice);
        Log.e(TAG, "setNotice: ");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }


    @Override
    public void onClick(View view) {
        if (view == tvContinue) {
            if (!isConnected()) {
                Snackbar.make(frameCode, "Please make sure that you are connect to internet.", Snackbar.LENGTH_LONG).show();
            } else {
                doVerifyCode();
            }
        }

        if (view == tvResendCode) {
            resendCode();
        }

        if (view == ivGoBack) {
            onBackPressed();
        }
    }

    private void resendCode() {
        tvResendCode.setVisibility(View.GONE);
        lhAutoVerify.setVisibility(View.VISIBLE);
        setUpVerificationCallbacks();
        tvCodeStatus.setText(Constant.AUTH_RESENDING_CODE);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(country + phoneNumber, 60, TimeUnit.SECONDS, this, verificationCallbacks, resendToken);
    }

    private void doVerifyCode() {
        String otp = etCode.getText().toString();
        if (TextUtils.isEmpty(otp) || !TextUtils.isDigitsOnly(otp) || otp.length() != 6) {
            Toast.makeText(CodeActivity.this, "Please enter valid OTP.", Toast.LENGTH_LONG).show();
            return;
        }

        PhoneAuthCredential credential =
                PhoneAuthProvider.getCredential(phoneVerificationId, otp);
        signInWithPhoneAuthCredential(credential);

    }

    private void signInWithPhoneAuthCredential(final PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e(TAG, "onComplete: ");
                            Snackbar.make(frameCode, "Successful", Snackbar.LENGTH_LONG).show();
                            checkUser(firebaseAuth.getCurrentUser().getUid());
                        } else {
                            if (task.getException() instanceof
                                    FirebaseAuthInvalidCredentialsException) {
                                Log.e(TAG, "onComplete: ");
                                Snackbar.make(frameCode, "Invalid Credentials", Snackbar.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    private void checkUser(String uid) {
        Call<CheckUser> checkUserCall = authInterface.checkUser(uid);
        checkUserCall.enqueue(new Callback<CheckUser>() {
            @Override
            public void onResponse(Call<CheckUser> call, Response<CheckUser> response) {
                Log.e(TAG, "onResponse: "+response.code() + response.message());
                if (response.code() == 200) {
                    gotoLoanPage();
                } else if (response.code() == 400) {
                    gotoLoanApplication();
                } else {
                    Toast.makeText(CodeActivity.this, "Something went wrong " +response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CheckUser> call, Throwable t) {
                Log.e(TAG, "onFailure: "+t.getMessage());
            }
        });
    }

    private void gotoLoanPage() {
        Intent intent = new Intent(CodeActivity.this, PLActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void gotoLoanApplication() {
        Intent intent = new Intent(CodeActivity.this, KycOnBoardActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public boolean isConnected() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

}