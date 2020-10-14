package com.payzout.customer.modules.kyc.residence;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResidenseResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;

    /**
     * No args constructor for use in serialization
     */
    public ResidenseResponse() {
    }

    /**
     * @param status
     */
    public ResidenseResponse(Boolean status) {
        super();
        this.status = status;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

}