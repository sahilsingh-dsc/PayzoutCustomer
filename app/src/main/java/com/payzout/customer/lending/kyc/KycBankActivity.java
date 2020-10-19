package com.payzout.customer.lending.kyc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.CustomerInterface;
import com.payzout.customer.apis.RazorpayInterface;
import com.payzout.customer.lending.model.IfscCheckList;
import com.payzout.customer.lending.model.KycBankList;
import com.payzout.customer.utils.PayzoutLoading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KycBankActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etAccountNumber;
    private EditText etConfirmAccount;
    private EditText etIfscCode;

    private TextView tvSaveAccountDetails;

    private SharedPreferences preferences;
    String user_id;
    private static final String TAG = "KycBankActivity";

    private boolean accountStatus = false;
    private boolean confirmAccountStatus = false;
    private boolean ifscStatus = false;
    String accountNumber;
    private FirebaseAuth firebaseAuth;
    String confirmAccount;
    String ifscCode;
    private ImageView ivGoBack;
    private PayzoutLoading payzoutLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_bank);
        preferences = getSharedPreferences("kyc", 0);

        initViews();
    }

    private void initViews() {
        payzoutLoading = PayzoutLoading.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();
        etAccountNumber = findViewById(R.id.etAccountNumber);
        etConfirmAccount = findViewById(R.id.etConfirmAccount);
        etIfscCode = findViewById(R.id.etIfscCode);
        ivGoBack = findViewById(R.id.ivGoBack);
        ivGoBack.setOnClickListener(this);

        tvSaveAccountDetails = findViewById(R.id.tvSaveAccountDetails);

        etAccountNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                accountNumber = etAccountNumber.getText().toString();
                if (accountNumber.isEmpty()) {
                    accountStatus = false;
                } else {
                    accountStatus = true;
                }
                validateBank();

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        etConfirmAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                confirmAccount = etConfirmAccount.getText().toString();
                if (confirmAccount.isEmpty()) {
                    confirmAccountStatus = false;
                } else {
                    if (accountNumber.equals(confirmAccount)) {
                        confirmAccountStatus = true;
                    } else {
                        confirmAccountStatus = false;
                    }
                }
                validateBank();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        etIfscCode.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                ifscCode = etIfscCode.getText().toString();
                if (ifscCode.isEmpty()) {
                    ifscStatus = false;
                } else if (ifscCode.length() == 11) {
                    ifscStatus = true;
                } else {
                    ifscStatus = false;
                }
                validateIFSC(ifscCode);
                validateBank();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });


        tvSaveAccountDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                payzoutLoading.showProgress(KycBankActivity.this);
                doSaveBank();
            }
        });
    }

    private void doSaveBank() {
        CustomerInterface customerInterface = APIClient.getRetrofitInstance().create(CustomerInterface.class);
        Call<KycBankList> kycBankListCall = customerInterface.kycBankList("adv", confirmAccount, ifscCode, user_id);
        kycBankListCall.enqueue(new Callback<KycBankList>() {
            @Override
            public void onResponse(Call<KycBankList> call, Response<KycBankList> response) {
                payzoutLoading.hideProgress();
                Log.e(TAG, "onResponse: " + response.code() + response.body() + response.message());
                if (response.code() == 200) {
                    Intent intent = new Intent(KycBankActivity.this, KycSubmitForVerificationActivity.class);
                    startActivity(intent);
                } else if (response.code() == 400) {
                    Toast.makeText(KycBankActivity.this, "Account Number already in use with other user", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<KycBankList> call, Throwable t) {
                payzoutLoading.hideProgress();
                Toast.makeText(KycBankActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void validateBank() {
        if (accountStatus && confirmAccountStatus && ifscStatus) {
            tvSaveAccountDetails.setEnabled(true);
            tvSaveAccountDetails.setBackground(getResources().getDrawable(R.drawable.bg_button));

        } else {
            tvSaveAccountDetails.setEnabled(false);
            tvSaveAccountDetails.setBackground(getResources().getDrawable(R.drawable.bg_button_disabled));
        }
    }

    private void validateIFSC(String ifscCode) {
        RazorpayInterface razorpayInterface = APIClient.getRazorPayInstance().create(RazorpayInterface.class);
        Call<IfscCheckList> call = razorpayInterface.fetechIfscdetails(ifscCode);
        call.enqueue(new Callback<IfscCheckList>() {
            @Override
            public void onResponse(Call<IfscCheckList> call, Response<IfscCheckList> response) {
                if (response.code() == 200) {
                    etIfscCode.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_check_circle_24, 0);
                    Log.e(TAG, "onResponse: " + response.body().getADDRESS());
                } else if (response.code() == 404) {
                    etIfscCode.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_report_problem_24, 0);
                    Log.e(TAG, "onResponse: IFSC not found");
                }
            }

            @Override
            public void onFailure(Call<IfscCheckList> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        if (view == ivGoBack) {
            onBackPressed();
        }
    }
}