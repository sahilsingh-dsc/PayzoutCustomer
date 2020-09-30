package com.payzout.customer.modules.kyc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.payzout.customer.R;
import com.payzout.customer.modules.kyc.basic.BasicDetailsActivity;

public class KycActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout lvBasicDetails;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kyc);
        initView();
    }

    private void initView() {
        lvBasicDetails = findViewById(R.id.lvBasicDetails);
        lvBasicDetails.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == lvBasicDetails) {
            Intent intent = new Intent(KycActivity.this, BasicDetailsActivity.class);
            startActivity(intent);

           // Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        }
    }
}