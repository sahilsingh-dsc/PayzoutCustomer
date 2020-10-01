package com.payzout.customer.modules.kyc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.payzout.customer.R;

public class LanguageAdapter extends BaseAdapter {

    private Context context;
    private String[] language;
    private LayoutInflater inflter;

    public LanguageAdapter(Context context, String[] language) {
        this.context = context;
        this.language = language;
        inflter = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return language.length;
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

        convertView = inflter.inflate(R.layout.language_item, parent, false);
        TextView tvItems = convertView.findViewById(R.id.tvItems);
        tvItems.setText(language[position]);
        return convertView;
    }
}
