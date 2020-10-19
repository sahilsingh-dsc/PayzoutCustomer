package com.payzout.customer.lending.kyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.CustomerInterface;
import com.payzout.customer.lending.model.CustomerStatus;
import com.payzout.customer.utils.Constant;
import com.payzout.customer.utils.PayzoutLoading;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KycSubmitForVerificationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvSubmitVerification;
    private PayzoutLoading payzoutLoading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_submit_for_verification);
        initView();
    }

    private void initView() {
        payzoutLoading = PayzoutLoading.getInstance();
        CheckBox checkAgreement = findViewById(R.id.checkAgreement);
        tvSubmitVerification = findViewById(R.id.tvSubmitVerification);

        checkAgreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tvSubmitVerification.setEnabled(true);
                    tvSubmitVerification.setBackground(getResources().getDrawable(R.drawable.bg_button));
                } else {
                    tvSubmitVerification.setEnabled(false);
                    tvSubmitVerification.setBackground(getResources().getDrawable(R.drawable.bg_button_disabled));
                }
            }
        });

        tvSubmitVerification.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == tvSubmitVerification) {
            payzoutLoading.showProgress(KycSubmitForVerificationActivity.this);
            doSubmitForReview();
        }
    }

    private void doSubmitForReview() {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        String uid = firebaseAuth.getCurrentUser().getUid();
        CustomerInterface customerInterface = APIClient.getRetrofitInstance().create(CustomerInterface.class);
        Call<CustomerStatus> call = customerInterface.changeCustomerStatus(uid, Constant.KYC_PENDING);
        call.enqueue(new Callback<CustomerStatus>() {
            @Override
            public void onResponse(Call<CustomerStatus> call, Response<CustomerStatus> response) {
                payzoutLoading.hideProgress();
                if (response.code() == Constant.HTTP_RESPONSE_SUCCESS) {
                    Intent intent = new Intent(KycSubmitForVerificationActivity.this, KycPendingActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK| Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                } else if (response.code() == Constant.HTTP_RESPONSE_BAD_REQUEST) {
                    Toast.makeText(KycSubmitForVerificationActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<CustomerStatus> call, Throwable t) {
                Toast.makeText(KycSubmitForVerificationActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}