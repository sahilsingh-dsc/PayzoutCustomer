package com.payzout.customer.service;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerContact {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<CustomerContactResponse> data = null;

    /**
     * No args constructor for use in serialization
     */
    public CustomerContact() {
    }

    /**
     * @param data
     * @param status
     */
    public CustomerContact(Boolean status, List<CustomerContactResponse> data) {
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

    public List<CustomerContactResponse> getData() {
        return data;
    }

    public void setData(List<CustomerContactResponse> data) {
        this.data = data;
    }

}