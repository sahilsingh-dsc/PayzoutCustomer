package com.payzout.customer.apis;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofitRest;
    private static Retrofit retrofitRAZOR;
    private static final String BASE_URL = "http://34.94.156.171/";
    private static final String VALIDATOR_URL = "https://ifsc.razorpay.com/";

    public static Retrofit getRetrofitInstance() {
        if (retrofitRest == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofitRest = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofitRest;
    }
    public static Retrofit getRazorPayInstance() {
        if (retrofitRAZOR == null) {
            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();
            retrofitRAZOR = new retrofit2.Retrofit.Builder()
                    .baseUrl(VALIDATOR_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return retrofitRAZOR;
    }
}
