package com.payzout.customer.common;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import com.payzout.customer.R;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingActivity extends AppCompatActivity {
    private OnBoardingAdapter onBoardingAdapter;
    private ViewPager2 onBoardingViewPager;
    private LinearLayout layoutOnBoardingIndicators;
    private TextView tvSkip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding);
        initViews();
    }

    private void initViews() {
        setOnBoardingItems();
        layoutOnBoardingIndicators = findViewById(R.id.layoutOnBoardingIndicators);
        onBoardingViewPager = findViewById(R.id.onBoardingViewPager);
        onBoardingViewPager.setAdapter(onBoardingAdapter);
        tvSkip = findViewById(R.id.tvSkip);
        setOnIndicators();
        setCurrentIndicator(0);

        onBoardingViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentIndicator(position);
            }
        });
        tvSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(OnBoardingActivity.this, "Clicked", Toast.LENGTH_SHORT).show();
                if (onBoardingViewPager.getCurrentItem() + 1 < onBoardingAdapter.getItemCount()) {
                    onBoardingViewPager.setCurrentItem(onBoardingViewPager.getCurrentItem() + 1);
                } else {
                    startActivity(new Intent(getApplicationContext(), PermissionActivity.class));
                    finish();
                }
            }
        });
    }

    private void setOnBoardingItems() {
        List<OnBoardingItem> onBoardingItems = new ArrayList<>();

        OnBoardingItem item = new OnBoardingItem();
        item.setDescription("Invest with Payzout business we provide a dedicated manager for you to help");
        item.setImage(R.drawable.slider_one);

        OnBoardingItem item1 = new OnBoardingItem();
        item1.setDescription("Invest in best, Payzout India's best peer to peer lending platform");
        item1.setImage(R.drawable.slider_two);

        OnBoardingItem item2 = new OnBoardingItem();
        item2.setDescription("Invest once and get profit every month with Payzout Business");
        item2.setImage(R.drawable.slider_3);

        onBoardingItems.add(item);
        onBoardingItems.add(item1);
        onBoardingItems.add(item2);

        onBoardingAdapter = new OnBoardingAdapter(onBoardingItems);
    }

    private void setOnIndicators() {
        ImageView[] indicators = new ImageView[onBoardingAdapter.getItemCount()];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < indicators.length; i++) {
            indicators[i] = new ImageView(getApplicationContext());
            indicators[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(), R.drawable.ondicator_inactive
            ));
            indicators[i].setLayoutParams(layoutParams);
            layoutOnBoardingIndicators.addView(indicators[i]);
        }
    }

    @SuppressLint("SetTextI18n")
    private void setCurrentIndicator(int index) {
        int childCount = layoutOnBoardingIndicators.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView = (ImageView) layoutOnBoardingIndicators.getChildAt(i);
            if (i == index) {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.ondicator_active)
                );
            } else {
                imageView.setImageDrawable(
                        ContextCompat.getDrawable(getApplicationContext(), R.drawable.ondicator_inactive)
                );
            }
        }
        if (index == onBoardingAdapter.getItemCount() - 1) {
            tvSkip.setText("Start");
        } else {
            tvSkip.setText("Next");
        }
    }

}