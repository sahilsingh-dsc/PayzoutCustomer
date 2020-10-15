package com.payzout.customer.apis;

import com.payzout.customer.auth.model.CheckCustomer;
import com.payzout.customer.lending.model.CustomerKycDetails;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface CustomerInterface {
    @FormUrlEncoded
    @POST("Customer/CheckCustomer")
    Call<CheckCustomer> checkCustomer(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Customer/submitKycDetails")
    Call<CustomerKycDetails> submitKyc(
            @Field("user_id") String user_id,
            @Field("full_name") String full_name,
            @Field("email") String email,
            @Field("gender") int getGender,
            @Field("marital_status") int getMarital,
            @Field("dob") String dobDate,
            @Field("residence_type") int residence_type,
            @Field("address") String address,
            @Field("city") String city,
            @Field("state") String state,
            @Field("pincode") int pincode,
            @Field("language") int getLanguage,
            @Field("education") int getEducation,
            @Field("employment_type") int employment_type,
            @Field("company_name") String company_name,
            @Field("monthly_salary") String monthly_salary,
            @Field("industry") String industry
    );
}
