package com.payzout.customer.modules.loan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.payzout.customer.R;

import java.util.List;

public class PLAmountAdapter extends RecyclerView.Adapter<PLAmountAdapter.ViewHolder> {

    private Context context;
    private List<PLAmount> plAmountList;

    public PLAmountAdapter(Context context, List<PLAmount> plAmountList) {
        this.context = context;
        this.plAmountList = plAmountList;
    }

    @NonNull
    @Override
    public PLAmountAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pl_amount_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PLAmountAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return plAmountList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
