package com.payzout.customer.lending.main.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.firebase.auth.FirebaseAuth;
import com.payzout.customer.R;
import com.payzout.customer.auth.PhoneActivity;

public class ManageAccountFragment extends Fragment implements View.OnClickListener {

    private View view;

    private ImageView ivLogout;
    private ImageView ivHelp;

    public ManageAccountFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_manage_account, container, false);
        initView();
        return view;
    }

    private void initView() {
        ivHelp = view.findViewById(R.id.ivHelp);
        ivHelp.setOnClickListener(this);
        ivLogout = view.findViewById(R.id.ivLogout);
        ivLogout.setOnClickListener(this);
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