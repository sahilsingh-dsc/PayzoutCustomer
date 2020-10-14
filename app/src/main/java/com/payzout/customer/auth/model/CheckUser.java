package com.payzout.customer.auth.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckUser {

    public static final int STATUS_USER_REJECTED = 1;
    public static final int STATUS_USER_BASIC = 1;
    public static final int STATUS_USER_RESIDENCE = 2;
    public static final int STATUS_USER_EMPLOYMENT = 3;
    public static final int STATUS_USER_BANK = 4;
    public static final int STATUS_USER_EKYC = 5;
    public static final int STATUS_USER_REVIEW_PENDING = 6;
    public static final int STATUS_USER_REVIEW_APPROVED = 7;
    public static final int STATUS_USER_ONGOING_LOAN = 8;

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("token")
    @Expose
    private String token;
    @SerializedName("info_verify_status")
    @Expose
    private Integer infoVerifyStatus;
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     */
    public CheckUser() {
    }

    /**
     * @param message
     * @param infoVerifyStatus
     * @param status
     * @param token
     */
    public CheckUser(Boolean status, String token, Integer infoVerifyStatus, String message) {
        super();
        this.status = status;
        this.token = token;
        this.infoVerifyStatus = infoVerifyStatus;
        this.message = message;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getInfoVerifyStatus() {
        return infoVerifyStatus;
    }

    public void setInfoVerifyStatus(Integer infoVerifyStatus) {
        this.infoVerifyStatus = infoVerifyStatus;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}