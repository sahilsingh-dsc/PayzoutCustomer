package com.payzout.customer.modules.kyc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.payzout.customer.R;

public class EductionAdapter extends BaseAdapter {
    private Context context;
    private String[] status;
    private LayoutInflater inflater;

    public EductionAdapter(Context context, String[] status) {
        this.context = context;
        this.status = status;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return status.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @SuppressLint({"InflateParams", "ViewHolder"})
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflater.inflate(R.layout.education_item, null);
        TextView tvItems = convertView.findViewById(R.id.tvItems);
        tvItems.setText(status[position]);
        return convertView;
    }
}
