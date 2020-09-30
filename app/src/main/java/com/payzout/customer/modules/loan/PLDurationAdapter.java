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

public class PLDurationAdapter extends RecyclerView.Adapter<PLDurationAdapter.PLViewHolder> {

    private Context context;
    private List<PLDuration> plDurationList;


    public PLDurationAdapter(Context context, List<PLDuration> plDurationList) {
        this.context = context;
        this.plDurationList = plDurationList;
    }

    @NonNull
    @Override
    public PLDurationAdapter.PLViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pl_duration_list_item, parent, false);
        return new PLViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PLDurationAdapter.PLViewHolder holder, int position) {
        PLDuration plDuration = plDurationList.get(position);
        if (plDuration.getType() == PLDuration.DURATION_TYPE_DAYS) {
            String duration = plDurationList.get(position).getDuration() +" "+ context.getResources().getString(R.string.str_days);
            holder.tvDuration.setText(duration);
        } else if (plDuration.getType() == PLDuration.DURATION_TYPE_MONTHS) {
            String duration = plDurationList.get(position).getDuration() +" "+ context.getResources().getString(R.string.str_months);
            holder.tvDuration.setText(duration);
        }

        if (plDuration.getStatus() == PLDuration.LOAN_STATUS_DURATION_LOCKED) {
            holder.tvDuration.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_lock_24, 0);
        } else if (plDuration.getStatus() == PLDuration.LOAN_STATUS_DURATION_OPENED) {
            holder.tvDuration.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_baseline_lock_open_24, 0);
        }

        if (plDuration.getStatus() == PLDuration.LOAN_STATUS_DURATION_LOCKED) {
            holder.tvDuration.setEnabled(false);
        } else if (plDuration.getStatus() == PLDuration.LOAN_STATUS_DURATION_OPENED) {
            holder.tvDuration.setEnabled(true);
        }

    }

    @Override
    public int getItemCount() {
        return plDurationList.size();
    }

    public class PLViewHolder extends RecyclerView.ViewHolder {

        private TextView tvDuration;
        public PLViewHolder(@NonNull View itemView) {
            super(itemView);

            tvDuration = itemView.findViewById(R.id.tvDuration);
        }
    }
}
