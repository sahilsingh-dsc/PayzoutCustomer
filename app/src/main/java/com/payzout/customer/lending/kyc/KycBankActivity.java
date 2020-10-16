package com.payzout.customer.lending.kyc;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.auth.FirebaseAuth;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.AuthInterface;
import com.payzout.customer.apis.CustomerInterface;
import com.payzout.customer.apis.RazorpayInterface;
import com.payzout.customer.lending.model.IfscCheckList;
import com.payzout.customer.lending.model.KycBankList;

public class KycBankActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText etAccountNumber;
    private EditText etConfirmAccount;
    private EditText etIfscCode;

    private TextView tvSaveAccountDetails;

    private LottieAnimationView aniLoadingSave;

    private SharedPreferences preferences;
    String user_id;
    private static final String TAG = "KycBankActivity";

    private boolean accountStatus = false;
    private boolean confirmAccountStatus = false;
    private boolean ifscStatus = false;

    private String accountNumber;
    private String confirmAccount;

    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_bank);
        preferences = getSharedPreferences("kyc", 0);

        initViews();
    }


    private void initViews() {
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            user_id = firebaseAuth.getCurrentUser().getUid();
        }
        etAccountNumber = findViewById(R.id.etAccountNumber);
        etConfirmAccount = findViewById(R.id.etConfirmAccount);
        etIfscCode = findViewById(R.id.etIfscCode);

        aniLoadingSave = findViewById(R.id.aniLoadingSave);
        tvSaveAccountDetails = findViewById(R.id.tvSaveAccountDetails);

        etAccountNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                 accountNumber = etAccountNumber.getText().toString();
                if (accountNumber.isEmpty()){
                    accountStatus = false;
                }else {
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
                if (confirmAccount.isEmpty()){
                    confirmAccountStatus = false;
                }else if (accountNumber.equals(confirmAccount)){
                    confirmAccountStatus = true;
                    Toast.makeText(KycBankActivity.this, "Your Account no. is matching", Toast.LENGTH_SHORT).show();
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
                String ifscCode = etIfscCode.getText().toString();
                if (ifscCode.isEmpty()){
                    ifscStatus = false;

                } else if(ifscCode.length() == 11)
                    {
                        ifscStatus = true;
                    }
                validateIFSC(ifscCode);
                validateBank();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        tvSaveAccountDetails.setOnClickListener(this);

    }

    private void saveBank() {
        String account_number = etAccountNumber.getText().toString();
        String ifsc_code = etIfscCode.getText().toString();
        CustomerInterface customerInterface = APIClient.getRetrofitInstance().create(CustomerInterface.class);
        Call<KycBankList> call = customerInterface.kycBankList(user_id, "Sahil Singh", account_number, ifsc_code);
        call.enqueue(new Callback<KycBankList>() {
            @Override
            public void onResponse(Call<KycBankList> call, Response<KycBankList> response) {
                Log.e(TAG, "onResponse: "+response );
            }

            @Override
            public void onFailure(Call<KycBankList> call, Throwable t) {

            }
        });
    }

    private void doHideLoading() {
        tvSaveAccountDetails.setText("");
        aniLoadingSave.setVisibility(View.VISIBLE);
        aniLoadingSave.playAnimation();
        aniLoadingSave.loop(true);
    }

    private void doShowLoading() {
        tvSaveAccountDetails.setText("Save");
        aniLoadingSave.setVisibility(View.GONE);
        aniLoadingSave.cancelAnimation();
        aniLoadingSave.loop(false);
    }

    private void validateIFSC(String ifscCode) {
        RazorpayInterface razorpayInterface = APIClient.getRazorpayInstance().create(RazorpayInterface.class);
        Call<IfscCheckList> call = razorpayInterface.fetchIfscDetails(ifscCode);
        call.enqueue(new Callback<IfscCheckList>() {
            @Override
            public void onResponse(Call<IfscCheckList> call, Response<IfscCheckList> response) {
                if (response.code() == 200) {
                    etIfscCode.setCompoundDrawablesWithIntrinsicBounds(0,  0, R.drawable.ic_baseline_check_circle_24,0);
                    Log.e(TAG, "onResponse: "+response.body().getADDRESS());
                } else if (response.code() == 404) {
                    etIfscCode.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_check_circle_red, 0);
                    Log.e(TAG, "onResponse: ifsc not found");
                }
            }

            @Override
            public void onFailure(Call<IfscCheckList> call, Throwable t) {

            }
        });
    }

    private void validateBank(){
        if (accountStatus && confirmAccountStatus && ifscStatus){
            tvSaveAccountDetails.setEnabled(true);
            tvSaveAccountDetails.setBackground(getResources().getDrawable(R.drawable.bg_button));

        }else {
            tvSaveAccountDetails.setEnabled(false);
            tvSaveAccountDetails.setBackground(getResources().getDrawable(R.drawable.bg_button_disabled));
        }
    }

    @Override
    public void onClick(View view) {
        if (view == tvSaveAccountDetails) {
            doShowLoading();
            saveBank();
        }
    }
}