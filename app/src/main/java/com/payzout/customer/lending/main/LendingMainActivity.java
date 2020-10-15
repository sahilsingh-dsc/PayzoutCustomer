package com.payzout.customer.lending.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.payzout.customer.R;
import com.payzout.customer.lending.main.fragment.ManageAccountFragment;
import com.payzout.customer.lending.main.fragment.RequestLoanFragment;

public class LendingMainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Fragment fragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lending_main);
        initView();
    }

    private void initView() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNav);
        bottomNav.setOnNavigationItemSelectedListener(this);
        fragment = new RequestLoanFragment();
        loadFragment(fragment);
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
}