package com.payzout.customer.apis;

import com.payzout.customer.lending.model.LoanOffer;
import com.payzout.customer.lending.model.LoanStatus;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface LoanInterface {
    @FormUrlEncoded
    @POST("Customer/getLoanOfferForCustomer")
    Call<LoanOffer> getLoanOffer(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Customer/getLoanStatus")
    Call<LoanStatus> getLoanStatus(@Field("loan_id") String loan_id);
}
