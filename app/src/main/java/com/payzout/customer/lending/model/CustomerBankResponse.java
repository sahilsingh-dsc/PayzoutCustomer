package com.payzout.customer.lending.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerBankResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name_on_account")
    @Expose
    private String nameOnAccount;
    @SerializedName("account_number")
    @Expose
    private Integer accountNumber;
    @SerializedName("ifsc")
    @Expose
    private Integer ifsc;
    @SerializedName("account_type")
    @Expose
    private Integer accountType;
    @SerializedName("is_approved")
    @Expose
    private Integer isApproved;
    @SerializedName("user_type")
    @Expose
    private Integer userType;

    /**
     * No args constructor for use in serialization
     */
    public CustomerBankResponse() {
    }

    /**
     * @param nameOnAccount
     * @param accountType
     * @param id
     * @param userType
     * @param accountNumber
     * @param ifsc
     * @param isApproved
     * @param userId
     */
    public CustomerBankResponse(String id, String userId, String nameOnAccount, Integer accountNumber, Integer ifsc, Integer accountType, Integer isApproved, Integer userType) {
        super();
        this.id = id;
        this.userId = userId;
        this.nameOnAccount = nameOnAccount;
        this.accountNumber = accountNumber;
        this.ifsc = ifsc;
        this.accountType = accountType;
        this.isApproved = isApproved;
        this.userType = userType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNameOnAccount() {
        return nameOnAccount;
    }

    public void setNameOnAccount(String nameOnAccount) {
        this.nameOnAccount = nameOnAccount;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getIfsc() {
        return ifsc;
    }

    public void setIfsc(Integer ifsc) {
        this.ifsc = ifsc;
    }

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }

    public Integer getIsApproved() {
        return isApproved;
    }

    public void setIsApproved(Integer isApproved) {
        this.isApproved = isApproved;
    }

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }

}