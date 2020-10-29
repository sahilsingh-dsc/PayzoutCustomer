package com.payzout.customer.lending.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.payzout.customer.R;
import com.payzout.customer.lending.main.fragment.ManageAccountFragment;
import com.payzout.customer.lending.main.fragment.RequestLoanFragment;

public class LendingMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private Fragment fragment = null;
    private ImageView ivLoan;
    private ImageView ivUser;
    private ImageView ivRepayment;
    private ImageView ivHome;

    private TextView tvLoan;
    private TextView tvHome;
    private TextView tvRepayment;
    private TextView tvProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lending_main);
        initView();
    }

    private void initView() {
        //BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        // bottomNav.setOnNavigationItemSelectedListener(this);
        fragment = new RequestLoanFragment();
        loadFragment(fragment);

        ivLoan = findViewById(R.id.ivLoan);
        ivUser = findViewById(R.id.ivUser);

        tvLoan = findViewById(R.id.tvLoan);
        tvHome = findViewById(R.id.tvHome);
        tvRepayment = findViewById(R.id.tvRepayment);
        tvProfile = findViewById(R.id.tvProfile);

        ivUser.setOnClickListener(this);
        ivLoan.setOnClickListener(this);
        ivRepayment.setOnClickListener(this);
        ivHome.setOnClickListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.footer_menu_loan:
                checkLoanStatus();
                break;

            case R.id.footer_menu_account:
                fragment = new ManageAccountFragment();
                loadFragment(fragment);
        }
        return true;
    }

    private void checkLoanStatus() {
        fragment = new RequestLoanFragment();
        loadFragment(fragment);
    }

    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frameContent, fragment)
                    .commit();
        }

    }

    @Override
    public void onClick(View v) {
        if (v == ivUser) {
            fragment = new ManageAccountFragment();
            loadFragment(fragment);
            switchUser();
        }

        if (v == ivLoan) {
            fragment = new RequestLoanFragment();
            loadFragment(fragment);
            switchLoan();
        }

        if (v == ivHome) {
            switchHome();
        }

        if (v == ivRepayment) {

            switchRepayment();
        }
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void switchRepayment() {
        ivHome.setImageDrawable(getResources().getDrawable(R.drawable.house));
        ivUser.setImageDrawable(getResources().getDrawable(R.drawable.user));
        ivLoan.setImageDrawable(getResources().getDrawable(R.drawable.loan));
        ivRepayment.setImageDrawable(getResources().getDrawable(R.drawable.return_active));

        tvHome.setTextColor(getResources().getColor(R.color.colorTextH1));
        tvLoan.setTextColor(getResources().getColor(R.color.colorTextH1));
        tvProfile.setTextColor(getResources().getColor(R.color.colorTextH1));
        tvRepayment.setTextColor(getResources().getColor(R.color.colorPrimary));

    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void switchUser() {
        ivHome.setImageDrawable(getResources().getDrawable(R.drawable.house));
        ivUser.setImageDrawable(getResources().getDrawable(R.drawable.user_active));
        ivLoan.setImageDrawable(getResources().getDrawable(R.drawable.loan));
        ivRepayment.setImageDrawable(getResources().getDrawable(R.drawable.repayement));

        tvHome.setTextColor(getResources().getColor(R.color.colorTextH1));
        tvLoan.setTextColor(getResources().getColor(R.color.colorTextH1));
        tvProfile.setTextColor(getResources().getColor(R.color.colorPrimary));
        tvRepayment.setTextColor(getResources().getColor(R.color.colorTextH1));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void switchLoan() {
        ivHome.setImageDrawable(getResources().getDrawable(R.drawable.house));
        ivUser.setImageDrawable(getResources().getDrawable(R.drawable.user));
        ivLoan.setImageDrawable(getResources().getDrawable(R.drawable.loan_active));
        ivRepayment.setImageDrawable(getResources().getDrawable(R.drawable.repayement));

        tvHome.setTextColor(getResources().getColor(R.color.colorTextH1));
        tvLoan.setTextColor(getResources().getColor(R.color.colorPrimary));
        tvProfile.setTextColor(getResources().getColor(R.color.colorTextH1));
        tvRepayment.setTextColor(getResources().getColor(R.color.colorTextH1));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void switchHome() {
        ivHome.setImageDrawable(getResources().getDrawable(R.drawable.home_active));
        ivUser.setImageDrawable(getResources().getDrawable(R.drawable.user));
        ivLoan.setImageDrawable(getResources().getDrawable(R.drawable.loan));
        ivRepayment.setImageDrawable(getResources().getDrawable(R.drawable.repayement));

        tvHome.setTextColor(getResources().getColor(R.color.colorPrimary));
        tvLoan.setTextColor(getResources().getColor(R.color.colorTextH1));
        tvProfile.setTextColor(getResources().getColor(R.color.colorTextH1));
        tvRepayment.setTextColor(getResources().getColor(R.color.colorTextH1));

    }
}