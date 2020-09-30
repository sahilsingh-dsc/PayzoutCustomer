package com.payzout.customer.modules.loan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        PLAmount plAmount = plAmountList.get(position);
        holder.tvAmount.setText(String.valueOf(plAmount.getAmount()));

        if (plAmount.getStatus() == PLAmount.LOAN_STATUS_AMOUNT_LOCKED) {
            holder.tvAmount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_lock_24, 0);
        } else if (plAmount.getStatus() == PLAmount.LOAN_STATUS_AMOUNT_OPENED) {
            holder.tvAmount.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_lock_open_24, 0);
        }

        if (plAmount.getStatus() == PLAmount.LOAN_STATUS_AMOUNT_LOCKED) {
            holder.tvAmount.setEnabled(false);
        } else if (plAmount.getStatus() == PLAmount.LOAN_STATUS_AMOUNT_OPENED) {
            holder.tvAmount.setEnabled(true);
        }

    }

    @Override
    public int getItemCount() {
        return plAmountList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvAmount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvAmount = itemView.findViewById(R.id.tvAmount);
        }
    }
}
