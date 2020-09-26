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
        holder.tvDuration.setText(plDurationList.get(position).getDuration());
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
