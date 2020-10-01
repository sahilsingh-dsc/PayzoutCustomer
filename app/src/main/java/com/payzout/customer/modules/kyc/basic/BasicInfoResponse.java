
package com.payzout.customer.modules.kyc.basic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicInfoResponse {

    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("first_name")
    @Expose
    private String firstName;
    @SerializedName("last_name")
    @Expose
    private String lastName;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("gender")
    @Expose
    private Integer gender;
    @SerializedName("dob")
    @Expose
    private String dob;
    @SerializedName("marital_status")
    @Expose
    private Integer maritalStatus;
    @SerializedName("pan_no")
    @Expose
    private String panNo;
    @SerializedName("aadhaar_no")
    @Expose
    private String aadharNo;
    @SerializedName("father_name")
    @Expose
    private String fatherName;
    @SerializedName("mother_name")
    @Expose
    private String motherName;
    @SerializedName("info_verify_status")
    @Expose
    private Integer infoVerifyStatus;
    @SerializedName("token")
    @Expose
    private String token;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BasicInfoResponse() {
    }

    /**
     * 
     * @param lastName
     * @param fatherName
     * @param gender
     * @param panNo
     * @param motherName
     * @param aadharNo
     * @param userId
     * @param infoVerifyStatus
     * @param token
     * @param firstName
     * @param dob
     * @param email
     * @param maritalStatus
     */
    public BasicInfoResponse(String userId, String firstName, String lastName, String email, Integer gender, String dob, Integer maritalStatus, String panNo, String aadharNo, String fatherName, String motherName, Integer infoVerifyStatus, String token) {
        super();
        this.userId = userId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.dob = dob;
        this.maritalStatus = maritalStatus;
        this.panNo = panNo;
        this.aadharNo = aadharNo;
        this.fatherName = fatherName;
        this.motherName = motherName;
        this.infoVerifyStatus = infoVerifyStatus;
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public Integer getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(Integer maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getAadharNo() {
        return aadharNo;
    }

    public void setAadharNo(String aadharNo) {
        this.aadharNo = aadharNo;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMotherName() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName = motherName;
    }

    public Integer getInfoVerifyStatus() {
        return infoVerifyStatus;
    }

    public void setInfoVerifyStatus(Integer infoVerifyStatus) {
        this.infoVerifyStatus = infoVerifyStatus;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

}
