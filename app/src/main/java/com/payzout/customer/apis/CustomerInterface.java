package com.payzout.customer.apis;

import com.payzout.customer.auth.model.CheckCustomer;
import com.payzout.customer.lending.model.AddCustomerReference;
import com.payzout.customer.lending.model.CustomerBank;
import com.payzout.customer.lending.model.CustomerKycDetails;
import com.payzout.customer.lending.model.CustomerStatus;
import com.payzout.customer.lending.model.KycBankList;
import com.payzout.customer.lending.model.LoanRecords;
import com.payzout.customer.lending.model.UserAB;
import com.payzout.customer.lending.model.UserAF;
import com.payzout.customer.lending.model.UserDL;
import com.payzout.customer.lending.model.UserPAN;
import com.payzout.customer.lending.model.UserSelfie;
import com.payzout.customer.service.CustomerContact;

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
            @Field("industry") String industry);

    @FormUrlEncoded
    @POST("Customer/submitAadhaarCardBack")
    Call<UserAB> submitUserAB(@Field("user_id") String user_id,
                              @Field("aadhaar_url") String aadhaar_url);

    @FormUrlEncoded
    @POST("Customer/submitAadhaarCardFront")
    Call<UserAF> submitUserAF(
            @Field("user_id") String user_id,
            @Field("aadhaar_url") String aadhaar_url);

    @FormUrlEncoded
    @POST("Customer/submitDrivingLicense")
    Call<UserDL> submitUserDl(
            @Field("user_id") String user_id,
            @Field("dl_url") String dl_url,
            @Field("dl_no") String dl_no);

    @FormUrlEncoded
    @POST("Customer/submitPanCard")
    Call<UserPAN> submitUserPan(@Field("user_id") String user_id, @Field("pan_url") String pan_url);

    @FormUrlEncoded
    @POST("Customer/submitSelfie")
    Call<UserSelfie> submitSelfie(
            @Field("user_id") String user_id,
            @Field("selfie_url") String selfie_url);

    @FormUrlEncoded
    @POST("Customer/addCustomerReference")
    Call<AddCustomerReference> addCustomerReference(
        @Field("reference_type1") String reference_type1,
        @Field("name1") String name1,
        @Field("phone_no1") String phone_no1,
        @Field("reference_type2") String reference_type2,
        @Field("name2") String name2,
        @Field("phone_no2") String phone_no2,
        @Field("id") String id,
        @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Customer/submitBankAccountDetails")
    Call<KycBankList> kycBankList(
            @Field("name_on_account") String name_on_account,
            @Field("account_number") String account_no,
            @Field("ifsc") String ifsc,
      @Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Customer/customerContacts")
    Call<CustomerContact> saveCustomerContacts(
            @Field("user_id")  String user_id,
            @Field("id") String id,
            @Field("name") String name,
            @Field("phone") String phone);

    @FormUrlEncoded
    @POST("Customer/changeCustomerStatus")
    Call<CustomerStatus> changeCustomerStatus(@Field("user_id") String user_id, @Field("status") int status);

    @FormUrlEncoded
    @POST("Customer/customerBanklist")
    Call<CustomerBank> getCustomerBanks(@Field("user_id") String user_id);

    @FormUrlEncoded
    @POST("Customer/getLoanRecords")
    Call<LoanRecords> getLoanRecords(@Field("user_id") String user_id);
}
