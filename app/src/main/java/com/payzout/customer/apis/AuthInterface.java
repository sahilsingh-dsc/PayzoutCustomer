package com.payzout.customer.apis;

import com.payzout.customer.auth.model.CheckUser;
import com.payzout.customer.modules.kyc.basic.BasicInfo;
import com.payzout.customer.modules.kyc.employment.EmploymentResponse;
import com.payzout.customer.modules.kyc.residence.ResidenseResponse;
import com.payzout.customer.modules.kyc.residence.State;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
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

    @GET("Auth/StateList")
    Call<State> getState(@Header("Access-Token") String tokens);

    @FormUrlEncoded
    @POST("Auth/submitResidentialAddress")
    Call<ResidenseResponse> submitResidentialAddress(
            @Header("Access-Token") String tokens,
            @Field("residence_type") int residence_type,
            @Field("address_line1") String address_line1,
            @Field("address_line2") String address_line2,
            @Field("state") int state,
            @Field("city") String city,
            @Field("locality") String locality,
            @Field("pincode") String pincode);

    @FormUrlEncoded
    @POST("Auth/submitEmploymentDetails")
    Call<EmploymentResponse> submitEmploymentDetails(
            @Header("Access-Token") String tokens,
            @Field("emp_status") int emp_status,
            @Field("monthly_income") String monthly_income,
            @Field("company_name") String company_name,
            @Field("work_expereinece") String work_expereinece,
            @Field("bank_statement") int bank_statement,
            @Field("office_address_line1") String office_address_line1,
            @Field("office_address_line2") String office_address_line2,
            @Field("office_state") int office_state,
            @Field("office_locality") String office_locality);


}
