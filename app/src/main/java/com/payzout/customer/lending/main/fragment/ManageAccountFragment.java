package com.payzout.customer.lending.main.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.payzout.customer.R;
import com.payzout.customer.apis.APIClient;
import com.payzout.customer.apis.CustomerInterface;
import com.payzout.customer.auth.PhoneActivity;
import com.payzout.customer.lending.main.LoanRecordAdapter;
import com.payzout.customer.lending.model.CustomerProfile;
import com.payzout.customer.lending.model.LoanRecords;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageAccountFragment extends Fragment implements View.OnClickListener {

    private View view;

    private TextView tvName;

    private ImageView ivLogout;
    private ImageView ivHelp;

    private LinearLayout lvNoData;

    private RecyclerView recyclerPortfolio;

    private String user_id;
    private FirebaseAuth firebaseAuth;

    private static final String TAG = "ManageAccountFragment";


    public ManageAccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manage_account, container, false);

        initView();
        return view;
    }

    private void initView() {
        tvName = view.findViewById(R.id.tvName);
        ivHelp = view.findViewById(R.id.ivHelp);
        ivHelp.setOnClickListener(this);
        ivLogout = view.findViewById(R.id.ivLogout);
        ivLogout.setOnClickListener(this);
        firebaseAuth = FirebaseAuth.getInstance();
        user_id = firebaseAuth.getCurrentUser().getUid();
        recyclerPortfolio = view.findViewById(R.id.recyclerPortfolio);
        recyclerPortfolio.setLayoutManager(new LinearLayoutManager(getContext()));
        lvNoData = view.findViewById(R.id.lvNoData);



        CustomerInterface customerInterface = APIClient.getRetrofitInstance().create(CustomerInterface.class);
        Call<LoanRecords> loanRecordsCall = customerInterface.getLoanRecords("dps1214");
        loanRecordsCall.enqueue(new Callback<LoanRecords>() {
            @Override
            public void onResponse(Call<LoanRecords> call, Response<LoanRecords> response) {
                Log.e(TAG, "onResponse: " + response.code() + response.body() + response.message());

                if (response.code() == 200) {
                    recyclerPortfolio.setVisibility(View.VISIBLE);
                    lvNoData.setVisibility(View.GONE);
                    LoanRecordAdapter loanRecordAdapter = new LoanRecordAdapter(getContext(), response.body().getData());
                    loanRecordAdapter.notifyDataSetChanged();
                    recyclerPortfolio.setAdapter(loanRecordAdapter);

                } else if (response.code() == 400) {
                    recyclerPortfolio.setVisibility(View.GONE);
                    lvNoData.setVisibility(View.VISIBLE);
                    Toast.makeText(getContext(), "Nothing to show", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<LoanRecords> call, Throwable t) {
                Log.e(TAG, "onFailure: " + t.getMessage());
            }
        });


        Call<CustomerProfile> customerProfileCall = customerInterface.getCustomerProfile(user_id);
        customerProfileCall.enqueue(new Callback<CustomerProfile>() {
            @Override
            public void onResponse(Call<CustomerProfile> call, Response<CustomerProfile> response) {
                Log.e(TAG, "onResponse: " + response.code() + response.body() + response.message());
                if (response.code() == 200){
                    tvName.setText(response.body().getData().getFullName());

                }
            }

            @Override
            public void onFailure(Call<CustomerProfile> call, Throwable t) {

            }
        });

        recyclerPortfolio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public void onClick(View view) {
        if (view == ivLogout) {
            showLogoutDialog();
        }
        if (view == ivHelp) {
            showHelpBottomSheet();
        }
    }

    private void showHelpBottomSheet() {
        HelpBottomSheetFragment helpBottomSheetFragment = new HelpBottomSheetFragment();
        helpBottomSheetFragment.show(getChildFragmentManager(), "help");
    }

    private void showLogoutDialog() {
        new MaterialAlertDialogBuilder(getContext())
                .setTitle("Logout")
                .setMessage("Are you sure, want to logout?")
                .setPositiveButton("Logout", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        doLogout();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                })
                .show();
    }

    private void doLogout() {
        SharedPreferences preferences = getContext().getSharedPreferences("profile", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signOut();
        Intent intent = new Intent(getContext(), PhoneActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

}