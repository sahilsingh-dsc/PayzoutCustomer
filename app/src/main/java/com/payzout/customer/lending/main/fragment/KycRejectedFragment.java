package com.payzout.customer.lending.main.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.payzout.customer.R;


public class KycRejectedFragment extends Fragment {

    public KycRejectedFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_kyc_rejected, container, false);
    }
}