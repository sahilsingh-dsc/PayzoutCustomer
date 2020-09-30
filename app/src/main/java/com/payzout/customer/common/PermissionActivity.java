package com.payzout.customer.common;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.nabinbhandari.android.permissions.PermissionHandler;
import com.nabinbhandari.android.permissions.Permissions;
import com.payzout.customer.R;
import com.payzout.customer.auth.PhoneActivity;
import com.payzout.customer.modules.loan.PLActivity;

public class PermissionActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tvIAgree;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        initView();
    }

    private void initView() {
        firebaseAuth = FirebaseAuth.getInstance();
        CheckBox checkAgreement = findViewById(R.id.checkAgreement);
        tvIAgree = findViewById(R.id.tvIAgree);

        checkAgreement.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    tvIAgree.setEnabled(true);
                    tvIAgree.setBackground(getResources().getDrawable(R.drawable.bg_button));
                } else {
                    tvIAgree.setEnabled(false);
                    tvIAgree.setBackground(getResources().getDrawable(R.drawable.bg_button_disabled));
                }
            }
        });

        tvIAgree.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if (view == tvIAgree) {
            String[] permissions = {Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.CAMERA};
            Permissions.check(this/*context*/, permissions,
                    null/*rationale*/, null/*options*/,
                    new PermissionHandler() {
                        @Override
                        public void onGranted() {
                            if (firebaseAuth.getCurrentUser() != null) {
                                gotoMain();
                            } else {
                                gotoLogin();
                            }
                        }
                    });
        }
    }


    private void gotoLogin() {
        Intent intent = new Intent(PermissionActivity.this, PhoneActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoMain() {
        Intent intent = new Intent(PermissionActivity.this, PLActivity.class);
        startActivity(intent);
        finish();
    }

}