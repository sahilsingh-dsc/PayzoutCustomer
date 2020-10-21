package com.payzout.customer.lending.main;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.payzout.customer.R;
import com.payzout.customer.lending.kyc.KycPendingActivity;
import com.payzout.customer.lending.kyc.KycRejectedActivity;
import com.payzout.customer.lending.main.fragment.DisbursementFragment;
import com.payzout.customer.lending.main.fragment.LoanPaidFragment;
import com.payzout.customer.lending.main.fragment.ManageAccountFragment;
import com.payzout.customer.lending.main.fragment.RepaymentFragment;
import com.payzout.customer.lending.model.LoanRecords;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
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
    public void onBindViewHolder(@NonNull LoanRecordViewHolder holder, final int position) {


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



        holder.lvLoanRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (loanRecordsList.get(position).getStatus() == 0){
                    Intent intent = new Intent(context, KycRejectedActivity.class);
                    context.startActivity(intent);
                }

                else if (loanRecordsList.get(position).getStatus() == 1){
                    Intent intent = new Intent(context, KycPendingActivity.class);
                    context.startActivity(intent);
                }

                else if (loanRecordsList.get(position).getStatus() == 2){
                    Fragment fragment = new DisbursementFragment();
                    FragmentManager fragmentManager = ((LendingMainActivity) context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameContent, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

                else if (loanRecordsList.get(position).getStatus() == 3){
                    Fragment fragment = new RepaymentFragment();
                    FragmentManager fragmentManager = ((LendingMainActivity) context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameContent, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

                else if (loanRecordsList.get(position).getStatus() == 4){
                    Fragment fragment = new RepaymentFragment();
                    FragmentManager fragmentManager = ((LendingMainActivity) context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameContent, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }

                else if (loanRecordsList.get(position).getStatus() == 5){
                    Fragment fragment = new LoanPaidFragment();
                    FragmentManager fragmentManager = ((LendingMainActivity) context).getSupportFragmentManager();
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                    fragmentTransaction.replace(R.id.frameContent, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return loanRecordsList.size();
    }

    public class LoanRecordViewHolder extends RecyclerView.ViewHolder {

        private TextView tvRecordAmount;
        private TextView tvRecordDate;
        private TextView tvRecordStatus;
        private LinearLayout lvLoanRecord;

        public LoanRecordViewHolder(@NonNull View itemView) {
            super(itemView);

            tvRecordAmount = itemView.findViewById(R.id.tvRecordAmount);
            tvRecordDate = itemView.findViewById(R.id.tvRecordDate);
            tvRecordStatus = itemView.findViewById(R.id.tvRecordStatus);
            lvLoanRecord = itemView.findViewById(R.id.lvLoanRecord);
        }
    }
}
