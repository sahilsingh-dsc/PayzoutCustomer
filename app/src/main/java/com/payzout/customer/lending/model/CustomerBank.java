package com.payzout.customer.lending.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerBank {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<CustomerBankResponse> data = null;

    /**
     * No args constructor for use in serialization
     */
    public CustomerBank() {
    }

    /**
     * @param data
     * @param status
     */
    public CustomerBank(Boolean status, List<CustomerBankResponse> data) {
        super();
        this.status = status;
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<CustomerBankResponse> getData() {
        return data;
    }

    public void setData(List<CustomerBankResponse> data) {
        this.data = data;
    }

}