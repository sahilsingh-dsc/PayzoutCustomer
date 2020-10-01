package com.payzout.customer.apis;

import com.payzout.customer.auth.model.CheckUser;
import com.payzout.customer.modules.kyc.basic.BasicInfo;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface AuthInterface {

    @FormUrlEncoded
    @POST("Auth/CheckUser")
    Call<CheckUser> checkUser(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Auth/submitBasicInfo")
    Call<BasicInfo> sendBasicInfo(
            @Field("user_id") String user_id,
            @Field("first_name") String firstName,
            @Field("last_name") String lastName,
            @Field("email") String email,
            @Field("gender") int getGender,
            @Field("marital_status") int getMarital,
            @Field("dob") String dobDate,
            @Field("pan_no") String panNo,
            @Field("aadhaar_no") String aadhaarNo,
            @Field("native_language") int getLanguage,
            @Field("education") int getEducation,
            @Field("father_name") String fatherName,
            @Field("mother_name") String motherName
    );

}
