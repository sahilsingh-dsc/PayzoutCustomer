package com.payzout.customer.modules.loan;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.payzout.customer.R;
import com.payzout.customer.modules.account.AccountActivity;

import java.util.ArrayList;
import java.util.List;

public class PLActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recyclerAmount;
    private RecyclerView recyclerDuration;
    private TextView tvContinue;
    private ImageView ivMenu;

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
        ivMenu = findViewById(R.id.ivMenu);

        recyclerAmount.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerDuration.setLayoutManager(new GridLayoutManager(this, 3));

        setPLAmountRecycler();
        setPLDurationRecycler();

        tvContinue.setOnClickListener(this);
        ivMenu.setOnClickListener(this);
    }

    private void setPLDurationRecycler() {
        List<PLDuration> plDurationList = new ArrayList<>();
        plDurationList.add(new PLDuration(1, 7, 1, 1));
        plDurationList.add(new PLDuration(2, 14, 1, 1));
        plDurationList.add(new PLDuration(3, 1, 2, 0));
        plDurationList.add(new PLDuration(4, 2, 2, 0));
        plDurationList.add(new PLDuration(5, 3, 2, 0));
        plDurationList.add(new PLDuration(6, 6, 2, 0));

        PLDurationAdapter plDurationAdapter = new PLDurationAdapter(PLActivity.this, plDurationList);
        recyclerDuration.setAdapter(plDurationAdapter);
    }

    private void setPLAmountRecycler() {
        List<PLAmount> plAmountList = new ArrayList<>();

        plAmountList.add(new PLAmount(1, 3000, 1));
        plAmountList.add(new PLAmount(1, 5000, 1));
        plAmountList.add(new PLAmount(1, 7000, 1));
        plAmountList.add(new PLAmount(1, 10000, 0));
        plAmountList.add(new PLAmount(1, 15000, 0));
        plAmountList.add(new PLAmount(1, 20000, 0));
        plAmountList.add(new PLAmount(1, 30000, 0));
        plAmountList.add(new PLAmount(1, 40000, 0));
        plAmountList.add(new PLAmount(1, 50000, 0));

        PLAmountAdapter plAmountAdapter = new PLAmountAdapter(PLActivity.this, plAmountList);
        recyclerAmount.setAdapter(plAmountAdapter);
    }

    @Override
    public void onClick(View view) {
        if (view == tvContinue) {
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }

        if (view == ivMenu) {
            Intent intent = new Intent(PLActivity.this, AccountActivity.class);
            startActivity(intent);
        }
    }
}