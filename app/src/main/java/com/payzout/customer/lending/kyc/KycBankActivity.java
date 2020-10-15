package com.payzout.customer.lending.kyc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.payzout.customer.R;

public class KycBankActivity extends AppCompatActivity {
    private EditText etAccountNumber;
    private EditText etConfirmAccount;
    private EditText etIfscCode;

    private TextView tvSaveAccountDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc_bank);
    }
}