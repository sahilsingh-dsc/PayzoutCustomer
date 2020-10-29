package com.payzout.customer.common;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.payzout.customer.R;

import java.util.List;

public class OnBoardingAdapter extends RecyclerView.Adapter<OnBoardingAdapter.OnBoardingViewHolder> {

    private List<OnBoardingItem> onBoardingItemList;

    public OnBoardingAdapter(List<OnBoardingItem> onBoardingItemList) {
        this.onBoardingItemList = onBoardingItemList;
    }

    @NonNull
    @Override
    public OnBoardingAdapter.OnBoardingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new OnBoardingViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.item_container_onboarding,
                        parent, false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull OnBoardingAdapter.OnBoardingViewHolder holder, int position) {
        holder.setOnBoardingData(onBoardingItemList.get(position));
    }

    @Override
    public int getItemCount() {
        return onBoardingItemList.size();
    }

    public class OnBoardingViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageOnboarding;
        private TextView textDescription;

        OnBoardingViewHolder(@NonNull View itemView) {
            super(itemView);

            imageOnboarding = itemView.findViewById(R.id.imageOnboarding);
            textDescription = itemView.findViewById(R.id.textDescription);
        }

        void setOnBoardingData(OnBoardingItem onBoardingItem) {
            textDescription.setText(onBoardingItem.getDescription());
            imageOnboarding.setImageResource(onBoardingItem.getImage());
        }

    }

}
