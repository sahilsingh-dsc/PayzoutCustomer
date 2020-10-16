package com.payzout.customer.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofitRazorpay;
    private static Retrofit retrofitREST;
    private static final String BASE_URL = "http://35.228.105.69/payzout/";
    private static final String VALIDATOR_URL = "https://ifsc.razorpay.com/";

    public static Retrofit getRetrofitInstance() {
        if (retrofitREST == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofitREST = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofitREST;
    }

    public static Retrofit getRazorpayInstance() {
        if (retrofitRazorpay == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofitRazorpay = new retrofit2.Retrofit.Builder()
                    .baseUrl(VALIDATOR_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofitRazorpay;
    }

}
