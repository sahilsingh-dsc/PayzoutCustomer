package com.payzout.customer.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.payzout.customer.R;

public class SpinnerDataAdapter extends BaseAdapter {
    private Context context;
    private String[] state;
    private LayoutInflater inflater;

    public SpinnerDataAdapter(Context context, String[] state) {
        this.context = context;
        this.state = state;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return state.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint({"ViewHolder", "InflateParams"})
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.spinner_item, null);
        TextView tvItems = view.findViewById(R.id.tvItems);
        tvItems.setText(state[i]);
        return view;
    }
}
