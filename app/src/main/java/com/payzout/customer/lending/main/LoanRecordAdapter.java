package com.payzout.customer.lending.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.payzout.customer.R;
import com.payzout.customer.lending.model.LoanRecords;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LoanRecordAdapter extends RecyclerView.Adapter<LoanRecordAdapter.LoanRecordViewHolder> {
   private Context context;
   private List<LoanRecords.Datum> loanRecordsList;

    public LoanRecordAdapter(Context context, List<LoanRecords.Datum> loanRecordsList) {
        this.context = context;
        this.loanRecordsList = loanRecordsList;
    }

    @NonNull
    @Override
    public LoanRecordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.loan_record_list_item, parent, false);
        return new LoanRecordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LoanRecordViewHolder holder, int position) {
        holder.tvRecordAmount.setText(""+loanRecordsList.get(position).getDisbursementAmount());
        holder.tvRecordDate.setText(""+loanRecordsList.get(position).getTimestamp());

        if (loanRecordsList.get(position).getStatus() == 0){
            holder.tvRecordStatus.setText("Rejected");
        }
        else if (loanRecordsList.get(position).getStatus() == 1){
            holder.tvRecordStatus.setText("Under Review");
        }
        else if (loanRecordsList.get(position).getStatus() == 2){
            holder.tvRecordStatus.setText("Waiting for Disbursement");
        }
        else if (loanRecordsList.get(position).getStatus() == 3){
            holder.tvRecordStatus.setText("Loan Disbursed");
        }
        else if (loanRecordsList.get(position).getStatus() == 4){
            holder.tvRecordStatus.setText("Overdue");
        }
        else if (loanRecordsList.get(position).getStatus() == 5){
            holder.tvRecordStatus.setText("Paid");
        }

    }

    @Override
    public int getItemCount() {
        return loanRecordsList.size();
    }

    public class LoanRecordViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRecordAmount;
        private TextView tvRecordDate;
        private TextView tvRecordStatus;

        public LoanRecordViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRecordAmount = itemView.findViewById(R.id.tvRecordAmount);
            tvRecordDate = itemView.findViewById(R.id.tvRecordDate);
            tvRecordStatus = itemView.findViewById(R.id.tvRecordStatus);
        }
    }
}
