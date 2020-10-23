package com.payzout.customer.lending.main.fragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.CustomerInterface;
import com.payzout.customer.apis.LoanInterface;
import com.payzout.customer.lending.model.CustomerBank;
import com.payzout.customer.lending.model.CustomerBankResponse;
import com.payzout.customer.lending.model.LoanOffer;
import com.payzout.customer.lending.model.LoanOfferResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
    private FirebaseAuth firebaseAuth;
    private static final String TAG = "RequestLoanFragment";

    private LoanOfferResponse gblLoanOfferResponse;
    private CustomerBankResponse gblCustomerBankResponse;

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

        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null) {
            String uid = firebaseAuth.getCurrentUser().getUid();
            Toast.makeText(getContext(), ""+uid, Toast.LENGTH_SHORT).show();
            getLoanOffer(uid);
            fetchCustomerBankAccount(uid);
        }
    }

    @Override
    public void onClick(View view) {
        if (view == tvRequestLoan) {
            showRequestDialog();
        }
    }

    private void getLoanOffer(String uid) {
        LoanInterface loanInterface = APIClient.getRetrofitInstance().create(LoanInterface.class);
        Call<LoanOffer> call = loanInterface.getLoanOffer("dps121447101200");
        call.enqueue(new Callback<LoanOffer>() {
            @Override
            public void onResponse(Call<LoanOffer> call, Response<LoanOffer> response) {
                Log.e(TAG, "onResponse: "+response );
                if (response.code() == 200) {
                    for (int i = 0; i < response.body().getData().size(); i++) {
                        LoanOfferResponse loanOfferResponse = response.body().getData().get(i);
                        gblLoanOfferResponse = loanOfferResponse;
                        setOfferData(loanOfferResponse);
                    }

                } else if (response.code() == 400) {

                }
            }

            @Override
            public void onFailure(Call<LoanOffer> call, Throwable t) {

            }
        });
    }

    private void setOfferData(LoanOfferResponse loanOfferResponse) {
        tvLoanAmount.setText("₹ "+loanOfferResponse.getAmount());
        tvDisbursalAmount.setText("₹ "+loanOfferResponse.getDisbursalAmount());
        tvRepaymentAmount.setText("₹ "+loanOfferResponse.getAmount());
        tvRepaymentDuration.setText(loanOfferResponse.getDuration()+" days");
        tvRepaymentDate.setText(loanOfferResponse.getRepaymentDate());
    }

    private void fetchCustomerBankAccount(String uid) {
        CustomerInterface customerInterface = APIClient.getRetrofitInstance().create(CustomerInterface.class);
        Call<CustomerBank> call = customerInterface.getCustomerBanks("dps121447101200");
        call.enqueue(new Callback<CustomerBank>() {
            @Override
            public void onResponse(Call<CustomerBank> call, Response<CustomerBank> response) {
                Log.e(TAG, "onResponse: "+response);
                for (int i = 0; i == 0; i++) {
                    CustomerBankResponse customerBankResponse = response.body().getData().get(i);
                    gblCustomerBankResponse = customerBankResponse;
                    setBankDetails(customerBankResponse);
                }
            }

            @Override
            public void onFailure(Call<CustomerBank> call, Throwable t) {

            }
        });
    }

    private void setBankDetails(CustomerBankResponse customerBankResponse) {
        tvAccountNumber.setText(customerBankResponse.getAccountNumber());
        tvIfscCode.setText(customerBankResponse.getIfsc());
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