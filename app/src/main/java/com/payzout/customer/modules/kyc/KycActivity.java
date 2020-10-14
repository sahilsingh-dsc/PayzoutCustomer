package com.payzout.customer.modules.kyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.payzout.customer.R;
import com.payzout.customer.auth.model.CheckUser;
import com.payzout.customer.common.Master;
import com.payzout.customer.modules.kyc.bank.BankingActivity;
import com.payzout.customer.modules.kyc.basic.BasicDetailsActivity;
import com.payzout.customer.modules.kyc.document.DocumentActivity;
import com.payzout.customer.modules.kyc.employment.EmploymentActivity;
import com.payzout.customer.modules.kyc.residence.ResidenceActivity;

public class KycActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout lvBasicDetails;
    private LinearLayout lvResidence;
    private LinearLayout lvEmployment;
    private LinearLayout lvBanking;
    private LinearLayout lvKyc;

    private ImageView ivBasic;
    private ImageView ivResi;
    private ImageView ivEmp;
    private ImageView ivBank;
    private ImageView ivKyc;

    private TextView tvSubmitForVerification;

    private Master master;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc);
        initView();
    }

    private void initView() {
        master = new Master(KycActivity.this);

        lvBasicDetails = findViewById(R.id.lvBasicDetails);
        lvResidence = findViewById(R.id.lvResidence);
        lvEmployment = findViewById(R.id.lvEmployment);
        lvBanking = findViewById(R.id.lvBanking);
        lvKyc = findViewById(R.id.lvKyc);

        ivBasic = findViewById(R.id.ivBasic);
        ivResi = findViewById(R.id.ivResi);
        ivEmp = findViewById(R.id.ivEmp);
        ivBank = findViewById(R.id.ivBank);
        ivKyc = findViewById(R.id.ivKyc);

        tvSubmitForVerification = findViewById(R.id.tvSubmitForVerification);

        lvBasicDetails.setOnClickListener(this);
        lvResidence.setOnClickListener(this);
        lvEmployment.setOnClickListener(this);
        lvBanking.setOnClickListener(this);
        lvKyc.setOnClickListener(this);
        tvSubmitForVerification.setOnClickListener(this);

        checkStatus();
    }

    private void checkStatus() {
        if (master.getStatus() == CheckUser.STATUS_USER_BASIC) {

            ivBasic.setVisibility(View.VISIBLE);
            ivResi.setVisibility(View.INVISIBLE);
            ivEmp.setVisibility(View.INVISIBLE);
            ivBank.setVisibility(View.INVISIBLE);
            ivKyc.setVisibility(View.INVISIBLE);

        } else if (master.getStatus() == CheckUser.STATUS_USER_RESIDENCE) {

            ivBasic.setVisibility(View.VISIBLE);
            ivResi.setVisibility(View.VISIBLE);
            ivEmp.setVisibility(View.INVISIBLE);
            ivBank.setVisibility(View.INVISIBLE);
            ivKyc.setVisibility(View.INVISIBLE);

        } else if (master.getStatus() == CheckUser.STATUS_USER_EMPLOYMENT) {

            ivBasic.setVisibility(View.VISIBLE);
            ivResi.setVisibility(View.VISIBLE);
            ivEmp.setVisibility(View.VISIBLE);
            ivBank.setVisibility(View.INVISIBLE);
            ivKyc.setVisibility(View.INVISIBLE);

        } else if (master.getStatus() == CheckUser.STATUS_USER_BANK) {

            ivBasic.setVisibility(View.VISIBLE);
            ivResi.setVisibility(View.VISIBLE);
            ivEmp.setVisibility(View.VISIBLE);
            ivBank.setVisibility(View.VISIBLE);
            ivKyc.setVisibility(View.INVISIBLE);

        } else if (master.getStatus() == CheckUser.STATUS_USER_EKYC) {

            ivBasic.setVisibility(View.VISIBLE);
            ivResi.setVisibility(View.VISIBLE);
            ivEmp.setVisibility(View.VISIBLE);
            ivBank.setVisibility(View.VISIBLE);
            ivKyc.setVisibility(View.VISIBLE);

            setSubmitButtonEnable();

        }
    }

    private void setSubmitButtonEnable() {
        tvSubmitForVerification.setBackground(getResources().getDrawable(R.drawable.bg_button));
        tvSubmitForVerification.setEnabled(true);
    }

    @Override
    public void onClick(View view) {
        if (view == lvBasicDetails) {
            gotoBasicDetails();
        }

        if (view == lvResidence) {
            gotoResidenceDetails();
        }

        if (view == lvEmployment) {
            gotoEmploymentDetails();
        }

        if (view == lvBanking) {
            gotoBankingDetails();
        }

        if (view == lvKyc) {
            gotoKyc();
        }

        if (view == tvSubmitForVerification) {
            doSubmitForVerification();
        }
    }

    private void gotoKyc() {
        Intent intent = new Intent(KycActivity.this, DocumentActivity.class);
        startActivity(intent);
    }

    private void gotoBankingDetails() {
        Intent intent = new Intent(KycActivity.this, BankingActivity.class);
        startActivity(intent);
    }

    private void gotoEmploymentDetails() {
        Intent intent = new Intent(KycActivity.this, EmploymentActivity.class);
        startActivity(intent);
    }

    private void gotoResidenceDetails() {
        Intent intent = new Intent(KycActivity.this, ResidenceActivity.class);
        startActivity(intent);
    }

    private void gotoBasicDetails() {
        Intent intent = new Intent(KycActivity.this, BasicDetailsActivity.class);
        startActivity(intent);
    }

    private void doSubmitForVerification() {

    }

    @Override
    protected void onResume() {
        super.onResume();
        checkForStatus();
    }

    private void checkForStatus() {
    }
}