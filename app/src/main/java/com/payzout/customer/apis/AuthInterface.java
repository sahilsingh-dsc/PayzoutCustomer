package com.payzout.customer.apis;

import com.payzout.customer.auth.model.CheckUser;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthInterface {

    @FormUrlEncoded
    @POST("Auth/CheckUser")
    Call<CheckUser> checkUser(@Field("user_id") String user_id);

}
