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

import com.google.firebase.auth.FirebaseAuth;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.AuthInterface;
import com.payzout.customer.apis.CustomerInterface;
import com.payzout.customer.lending.model.KycBankList;

public class KycBankActivity extends AppCompatActivity {
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_bank);
        preferences = getSharedPreferences("kyc", 0);

        initViews();
    }

    private void initViews() {
        etAccountNumber = findViewById(R.id.etAccountNumber);
        etConfirmAccount = findViewById(R.id.etConfirmAccount);
        etIfscCode = findViewById(R.id.etIfscCode);

        tvSaveAccountDetails = findViewById(R.id.tvSaveAccountDetails);

        etAccountNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String accountNumber = etAccountNumber.getText().toString();
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
                String confirmAccount = etConfirmAccount.getText().toString();
                if (confirmAccount.isEmpty()){
                    confirmAccountStatus = false;
                }else {
                    confirmAccountStatus = true;
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
                }else {
                    ifscStatus = true;
                }
                    validateBank();
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        tvSaveAccountDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CustomerInterface customerInterface = APIClient.getRetrofitInstance().create(CustomerInterface.class);

                Call<KycBankList> kycBankListCall = customerInterface.kycBankList(user_id);
                kycBankListCall.enqueue(new Callback<KycBankList>() {
                    @Override
                    public void onResponse(Call<KycBankList> call, Response<KycBankList> response) {
                        Log.e(TAG, "onResponse: " + response.code() + response.body() + response.message());
                        if (response.code() == 200){
                            Toast.makeText(KycBankActivity.this, "Bank Account added Successfully", Toast.LENGTH_SHORT).show();
                        }else if (response.code() == 400){
                            Toast.makeText(KycBankActivity.this, "Account Number already in use with other user", Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<KycBankList> call, Throwable t) {
                        Toast.makeText(KycBankActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });

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
}