package com.payzout.customer.modules.loan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.payzout.customer.R;

public class PLActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pl);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        initView();
    }

    private void initView() {

    }

    @Override
    public void onClick(View view) {

    }
}