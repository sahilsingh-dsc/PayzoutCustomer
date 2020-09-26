package com.payzout.customer.modules.loan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.payzout.customer.R;

import java.util.ArrayList;
import java.util.List;

public class PLActivity extends AppCompatActivity implements View.OnClickListener {

private RecyclerView recyclerAmount;
private RecyclerView recyclerDuration;
    private TextView tvContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pl);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        initView();
    }

    private void initView() {
        recyclerAmount = findViewById(R.id.recyclerAmount);
        recyclerDuration = findViewById(R.id.recyclerDuration);

        tvContinue = findViewById(R.id.tvContinue);

        recyclerAmount.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerDuration.setLayoutManager(new GridLayoutManager(this, 3));

        setPLAmountRecycler();
        setPLDurationRecycler();

        tvContinue.setOnClickListener(this);
    }

    private void setPLDurationRecycler() {
        List<PLDuration> plDurationList = new ArrayList<>();
        plDurationList.add(new PLDuration(1, "3 Months"));
        plDurationList.add(new PLDuration(1, "6 Months"));
        plDurationList.add(new PLDuration(1, "9 Months"));
        plDurationList.add(new PLDuration(1, "12 Months"));
        plDurationList.add(new PLDuration(1, "24 Months"));
        plDurationList.add(new PLDuration(1, "36 Months"));

        PLDurationAdapter plDurationAdapter = new PLDurationAdapter(PLActivity.this, plDurationList);
        recyclerDuration.setAdapter(plDurationAdapter);
    }

    private void setPLAmountRecycler() {
        List<PLAmount> plAmountList = new ArrayList<>();

        plAmountList.add(new PLAmount(1, 3000));
        plAmountList.add(new PLAmount(1, 5000));
        plAmountList.add(new PLAmount(1, 7000));
        plAmountList.add(new PLAmount(1, 10000));
        plAmountList.add(new PLAmount(1, 15000));
        plAmountList.add(new PLAmount(1, 20000));
        plAmountList.add(new PLAmount(1, 30000));
        plAmountList.add(new PLAmount(1, 40000));
        plAmountList.add(new PLAmount(1, 50000));

        PLAmountAdapter plAmountAdapter = new PLAmountAdapter(PLActivity.this, plAmountList);
        recyclerAmount.setAdapter(plAmountAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view == tvContinue){
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            startActivity(new Intent(PLActivity.this, EKYCActivity.class));
        }
    }
}