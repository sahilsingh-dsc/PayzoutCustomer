package com.payzout.customer.lending.main.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.LoanInterface;
import com.payzout.customer.lending.model.LoanStatus;

public class LoanPaidFragment extends Fragment {
    private View view;

    private TextView tvLoanPaidAmount;
    private TextView tvLoanPaidDate;
    private TextView tvPaidLoan;
    private TextView tvPaidDisbursalAmount;
    private TextView tvPaidProcessingFee;
    private TextView tvPaidGstFee;
    private TextView tvPaidInterest;
    private TextView tvPaidOverdue;
    private TextView tvPaidOverdueFee;
    private TextView tvPaidGstOverdue;
    private TextView tvPaidAmount;

    private FirebaseAuth firebaseAuth;
    private static final String TAG = "LoanPaidFragment";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_loan_paid, container, false);

        Bundle bundle = getArguments();
        String s = bundle.getString("id");

        initView();
        return view;

    }

    private void initView() {
        tvLoanPaidAmount = view.findViewById(R.id.tvLoanPaidAmount);
        tvLoanPaidDate = view.findViewById(R.id.tvLoanPaidDate);
        tvPaidLoan = view.findViewById(R.id.tvPaidLoan);
        tvPaidDisbursalAmount = view.findViewById(R.id.tvPaidDisbursalAmount);
        tvPaidProcessingFee = view.findViewById(R.id.tvPaidProcessingFee);
        tvPaidGstFee = view.findViewById(R.id.tvPaidGstFee);
        tvPaidInterest = view.findViewById(R.id.tvPaidInterest);
        tvPaidOverdue = view.findViewById(R.id.tvPaidOverdue);
        tvPaidOverdueFee = view.findViewById(R.id.tvPaidOverdueFee);
        tvPaidGstOverdue = view.findViewById(R.id.tvPaidGstOverdue);
        tvPaidAmount = view.findViewById(R.id.tvPaidAmount);

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            String lid = firebaseAuth.getCurrentUser().getUid();
            Toast.makeText(getContext(), ""+lid, Toast.LENGTH_SHORT).show();
            getLoanPaid();
        }
    }

    private void getLoanPaid() {
        LoanInterface loanInterface = APIClient.getRetrofitInstance().create(LoanInterface.class);
        Call<LoanStatus> loanStatusCall = loanInterface.getLoanStatus("JnXZ27lPdeyrb46L");
        loanStatusCall.enqueue(new Callback<LoanStatus>() {
            @Override
            public void onResponse(Call<LoanStatus> call, Response<LoanStatus> response) {
                Log.e(TAG, "onResponse: "+response );
                LoanStatus.Data loanStatus = response.body().getData();
                setLoanPaid(loanStatus);
            }

            @Override
            public void onFailure(Call<LoanStatus> call, Throwable t) {

            }
        });
    }

    private void setLoanPaid(LoanStatus.Data loanStatus) {
        tvLoanPaidAmount.setText("₹ "+loanStatus.getRepaybleAmount());
        tvLoanPaidDate.setText(loanStatus.getLastDateForRepayment());
        tvPaidLoan.setText("₹ "+loanStatus.getLoanAmount());
        tvPaidDisbursalAmount.setText("₹ "+loanStatus.getDisbursementAmount());
        tvPaidProcessingFee.setText("₹ "+loanStatus.getProcessingFee());
        tvPaidGstFee.setText("₹ "+loanStatus.getGstOnProcessingFee());
        tvPaidInterest.setText("₹ "+loanStatus.getInterestFee());
        tvPaidOverdue.setText(loanStatus.getOverdueDays()+ " days");
        tvPaidOverdueFee.setText("₹ "+loanStatus.getOverdueFee());
        tvPaidGstOverdue.setText("₹ "+loanStatus.getGstOverdueFee());
        tvPaidAmount.setText("₹ "+loanStatus.getRepaybleAmount());

    }
}