package com.payzout.customer.lending.kyc;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.payzout.customer.R;
import com.payzout.customer.service.ContactUploadService;

public class KycOnBoardActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvApplyNow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_on_board);
        initView();
    }

    private void initView() {
        tvApplyNow = findViewById(R.id.tvApplyNow);
        tvApplyNow.setOnClickListener(this);
        startContactService();
    }

    @Override
    public void onClick(View view) {
        if (view == tvApplyNow) {
            gotoKycDetails();
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

    private void startContactService() {
        Intent intentService = new Intent(KycOnBoardActivity.this, ContactUploadService.class);
       // startService(intentService);
    }
}