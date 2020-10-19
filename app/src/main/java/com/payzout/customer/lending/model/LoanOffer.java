package com.payzout.customer.lending.model;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanOffer {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<LoanOfferResponse> data = null;

    /**
     * No args constructor for use in serialization
     */
    public LoanOffer() {
    }

    /**
     * @param data
     * @param status
     */
    public LoanOffer(Boolean status, List<LoanOfferResponse> data) {
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

    public List<LoanOfferResponse> getData() {
        return data;
    }

    public void setData(List<LoanOfferResponse> data) {
        this.data = data;
    }

}