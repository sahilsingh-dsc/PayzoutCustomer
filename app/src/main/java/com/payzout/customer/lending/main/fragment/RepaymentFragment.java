package com.payzout.customer.lending.main.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.LoanInterface;
import com.payzout.customer.lending.model.LoanOfferResponse;
import com.payzout.customer.lending.model.LoanStatus;
import com.payzout.customer.lending.repayment.RepaymentActivity;
import com.payzout.customer.utils.PayzoutLoading;

public class RepaymentFragment extends Fragment implements View.OnClickListener {
    private View view;

    private TextView tvLoanRepayAmount;
    private TextView tvLoanRepayDate;
    private TextView tvLoanAmount;
    private TextView tvDisbursalAmount;
    private TextView tvProcessingFee;
    private TextView tvGstFee;
    private TextView tvTotalInterest;
    private TextView tvOverdueDay;
    private TextView tvOverdueFee;
    private TextView tvGstOverdue;
    private TextView tvTotalRepaymentAmount;
    private TextView tvRepayLoan;

    private ImageView ivGoBack;

    private PayzoutLoading payzoutLoading;

    private FirebaseAuth firebaseAuth;
    private static final String TAG = "RepaymentFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view =  inflater.inflate(R.layout.fragment_repayment, container, false);

        Bundle bundle = getArguments();
        String s = bundle.getString("id");

        initView();
        return view;
    }

    private void initView() {
        payzoutLoading = PayzoutLoading.getInstance();
        tvLoanRepayAmount = view.findViewById(R.id.tvLoanRepayAmount);
        tvLoanRepayDate = view.findViewById(R.id.tvLoanRepayDate);
        tvLoanAmount = view.findViewById(R.id.tvLoanAmount);
        tvDisbursalAmount = view.findViewById(R.id.tvDisbursalAmount);
        tvProcessingFee = view.findViewById(R.id.tvProcessingFee);
        tvGstFee = view.findViewById(R.id.tvGstFee);
        tvTotalInterest = view.findViewById(R.id.tvTotalInterest);
        tvOverdueDay = view.findViewById(R.id.tvOverdueDay);
        tvOverdueFee = view.findViewById(R.id.tvOverdueFee);
        tvGstOverdue = view.findViewById(R.id.tvGstOverdue);
        tvTotalRepaymentAmount = view.findViewById(R.id.tvTotalRepaymentAmount);

        tvRepayLoan = view.findViewById(R.id.tvRepayLoan);
        tvRepayLoan.setOnClickListener(this);

        ivGoBack = view.findViewById(R.id.ivGoBack);
        ivGoBack.setOnClickListener(this);


        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            String lid = firebaseAuth.getCurrentUser().getUid();
            Toast.makeText(getContext(), ""+lid, Toast.LENGTH_SHORT).show();
            getLoanStatus(lid);
        }

    }

    @Override
    public void onClick(View view){

        if (view == tvRepayLoan){
            showRequestDialog();
        }

        if (view == ivGoBack){
            onBackPressed();
        }

    }

    private void onBackPressed() {

    }


    private void showRequestDialog() {
        new MaterialAlertDialogBuilder(getContext())
                .setTitle("Repay Loan")
                .setMessage("Are you sure you want to proceed ?")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        doPayment();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    private void doPayment() {
        doShowLoading();
        doGotoRepaymentWindow();
    }

    private void doShowLoading() {
        payzoutLoading.showProgress(getContext());
    }

    private void doGotoRepaymentWindow() {
        doHideLoading();
        Intent intent = new Intent(getActivity(), RepaymentActivity.class);
        startActivity(intent);

    }

    private void doHideLoading() {
        payzoutLoading.hideProgress();
    }

    private void getLoanStatus(String lid) {
        LoanInterface loanInterface = APIClient.getRetrofitInstance().create(LoanInterface.class);
        Call<LoanStatus> loanStatusCall = loanInterface.getLoanStatus("JnXZ27lPdeyrb46L");
        loanStatusCall.enqueue(new Callback<LoanStatus>() {
            @Override
            public void onResponse(Call<LoanStatus> call, Response<LoanStatus> response) {
                Log.e(TAG, "onResponse: "+response );
                if (response.code() == 200){
                    LoanStatus.Data loanStatus = response.body().getData();
                    setLoanStatus(loanStatus);
                }
            }

            @Override
            public void onFailure(Call<LoanStatus> call, Throwable t) {

            }
        });
    }

    private void setLoanStatus(LoanStatus.Data loanStatus) {
        tvLoanRepayAmount.setText("₹ "+loanStatus.getRepaybleAmount());
        tvLoanAmount.setText("₹ "+loanStatus.getLoanAmount());
        tvLoanRepayDate.setText(loanStatus.getRepaymentDate());
        tvDisbursalAmount.setText("₹ "+loanStatus.getDisbursementAmount());
        tvProcessingFee.setText("₹ "+loanStatus.getProcessingFee());
        tvGstFee.setText("₹ "+loanStatus.getGstOnProcessingFee());
        tvTotalInterest.setText("₹ "+loanStatus.getInterestFee());
        tvOverdueFee.setText("₹ "+loanStatus.getOverdueFee());
        tvGstOverdue.setText("₹ "+loanStatus.getGstOverdueFee());
        tvTotalRepaymentAmount.setText("₹ "+loanStatus.getRepaybleAmount());
        tvOverdueDay.setText(loanStatus.getOverdueDays() +" days");
    }
}