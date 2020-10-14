package com.payzout.customer.lending.kyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.payzout.customer.R;
import com.payzout.customer.utils.Constant;

public class KycOnBoardActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvApplyNow;
    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_on_board);
        initView();
    }

    private void initView() {
        preferences = getSharedPreferences("kyc", 0);
        tvApplyNow = findViewById(R.id.tvApplyNow);
        tvApplyNow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == tvApplyNow) {
            checkKycStatus();
        }
    }

    private void checkKycStatus() {
        int kycStatus = preferences.getInt("kycStatus", Constant.KYC_NONE);
        if (kycStatus == Constant.KYC_NONE) {
            gotoKycDetails();
        }
        if (kycStatus == Constant.KYC_PARTIAL) {
            gotoKycDocuments();
        }
        if (kycStatus == Constant.KYC_FULL) {

        }
    }

    private void gotoKycDetails() {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(50);
        Intent intent = new Intent(KycOnBoardActivity.this, KycDetailsActivity.class);
        startActivity(intent);
    }

    private void gotoKycDocuments() {
        Vibrator vibe = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        vibe.vibrate(50);
        Intent intent = new Intent(KycOnBoardActivity.this, KycDocumentUploadActivity.class);
        startActivity(intent);
    }
}