package com.payzout.customer.common;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.CustomerInterface;
import com.payzout.customer.auth.PhoneActivity;
import com.payzout.customer.auth.model.CheckCustomer;
import com.payzout.customer.lending.kyc.KycOnBoardActivity;
import com.payzout.customer.modules.kyc.KycActivity;
import com.payzout.customer.modules.loan.PLActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private CustomerInterface customerInterface;
    private static final String TAG = "SplashActivity";
    private FrameLayout frameSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        initView();
    }

    private void initView() {
        ImageView ivLogo = findViewById(R.id.ivLogo);
        frameSplash = findViewById(R.id.frameSplash);
        Glide.with(SplashActivity.this).load(R.drawable.payzout_full).into(ivLogo);
        firebaseAuth = FirebaseAuth.getInstance();
        customerInterface = APIClient.getRetrofitInstance().create(CustomerInterface.class);
        delay3000milli();
    }

    private void delay3000milli() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                checkPermission();
            }
        }, 3000);
    }

    private void gotoLogin() {
        Intent intent = new Intent(SplashActivity.this, PhoneActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoMain() {
        Intent intent = new Intent(SplashActivity.this, PLActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoPermission() {
        Intent intent = new Intent(SplashActivity.this, PermissionActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoLoanApplication() {
        Intent intent = new Intent(SplashActivity.this, KycActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    private void checkPermission() {
        int phone = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_PHONE_STATE);
        int contact = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.READ_CONTACTS);
        int c_location = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int f_location = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.ACCESS_FINE_LOCATION);
        int camera = ContextCompat.checkSelfPermission(SplashActivity.this, Manifest.permission.CAMERA);
        if (phone == PackageManager.PERMISSION_GRANTED
                && contact == PackageManager.PERMISSION_GRANTED
                && c_location == PackageManager.PERMISSION_GRANTED
                && f_location == PackageManager.PERMISSION_GRANTED
                && camera == PackageManager.PERMISSION_GRANTED) {

            if (firebaseAuth.getCurrentUser() != null) {
                checkUser(firebaseAuth.getCurrentUser().getUid());
            } else {
                gotoLogin();
            }

        } else {
            gotoPermission();
        }
    }

    private void checkUser(String uid) {
        Call<CheckCustomer> call = customerInterface.checkCustomer(uid);
        call.enqueue(new Callback<CheckCustomer>() {
            @Override
            public void onResponse(Call<CheckCustomer> call, Response<CheckCustomer> response) {
                if (response.code() == 200) {
                    int status = response.body().getStatus();
                    if (status == 0) {
                        showCustomerBan();
                    } else if (status == 1) {
                        gotoLendingMain();
                    }
                } else if (response.code() == 400) {
                    gotoLendingMain();
                }
            }

            @Override
            public void onFailure(Call<CheckCustomer> call, Throwable t) {
                Toast.makeText(SplashActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void gotoLendingMain() {
        Intent intent = new Intent(SplashActivity.this, KycOnBoardActivity.class);
        startActivity(intent);
        finish();
    }

    private void showCustomerBan() {
        Snackbar.make(frameSplash, "Unauthorized Access", Snackbar.LENGTH_INDEFINITE).show();
    }


}