package com.payzout.customer.common;

import android.content.Context;
import android.content.SharedPreferences;

import com.payzout.customer.auth.model.CheckUser;

public class Master {
    Context context;

    public Master(Context context) {
        this.context = context;
    }

    public void setStatus(CheckUser checkUser) {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("token", checkUser.getToken());
        editor.putInt("status", checkUser.getInfoVerifyStatus());
        editor.apply();
    }

    public String getToken() {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return preferences.getString("token", "");
    }

    public int getStatus() {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        return preferences.getInt("status", 0);
    }

    public void userLogout() {
        SharedPreferences preferences = context.getSharedPreferences("user", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.apply();
    }

}
