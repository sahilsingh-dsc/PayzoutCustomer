package com.payzout.customer.lending.main.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.payzout.customer.R;

public class RequestLoanFragment extends Fragment implements View.OnClickListener {

    private View view;

    private TextView tvLoanAmount;
    private TextView tvDisbursalAmount;
    private TextView tvRepaymentDuration;
    private TextView tvRepaymentAmount;
    private TextView tvRepaymentDate;
    private TextView tvAccountNumber;
    private TextView tvIfscCode;

    private TextView tvRequestLoan;
    private LottieAnimationView aniLoading;


    public RequestLoanFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_loan_request, container, false);
        initView();
        return view;
    }

    private void initView() {
        tvLoanAmount = view.findViewById(R.id.tvLoanAmount);
        tvDisbursalAmount = view.findViewById(R.id.tvDisbursalAmount);
        tvRepaymentDuration = view.findViewById(R.id.tvRepaymentDuration);
        tvRepaymentAmount = view.findViewById(R.id.tvRepaymentAmount);
        tvRepaymentDate = view.findViewById(R.id.tvRepaymentDate);
        tvAccountNumber = view.findViewById(R.id.tvAccountNumber);
        tvIfscCode = view.findViewById(R.id.tvIfscCode);

        tvRequestLoan = view.findViewById(R.id.tvRequestLoan);
        tvRequestLoan.setOnClickListener(this);
        aniLoading = view.findViewById(R.id.aniLoading);
    }

    @Override
    public void onClick(View view) {
        if (view == tvRequestLoan) {
            showRequestDialog();
        }
    }

    private void showRequestDialog() {
        new MaterialAlertDialogBuilder(getContext())
                .setTitle("Request Loan")
                .setMessage("On confirming you will apply for loan, once processed we will deposit amount into you bank account.")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        doRequestForLoan();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                }).show();
    }

    private void doRequestForLoan() {
        doShowLoading();
        doDelay3000Mili();
    }

    private void doDelay3000Mili() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doHideLoading();
                Fragment fragment = new RepaymentFragment();
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frameContent, fragment);
                fragmentTransaction.commit();
            }
        }, 3000);
    }

    private void doShowLoading() {
        tvRequestLoan.setText("");
        aniLoading.setVisibility(View.VISIBLE);
        aniLoading.playAnimation();
        aniLoading.loop(true);
    }

    private void doHideLoading() {
        tvRequestLoan.setText("Request Loan");
        aniLoading.setVisibility(View.GONE);
        aniLoading.cancelAnimation();
        aniLoading.loop(false);
    }

}