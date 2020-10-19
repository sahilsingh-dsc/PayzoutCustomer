package com.payzout.customer.apis;

import com.payzout.customer.lending.model.IfscCheckList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RazorpayInterface {
    @GET("{ifsc}")
    Call<IfscCheckList> fetechIfscdetails(@Path("ifsc") String ifsc);
}
