package com.payzout.customer.modules.kyc.employment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EmploymentResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;

    /**
     * No args constructor for use in serialization
     */
    public EmploymentResponse() {
    }

    /**
     * @param status
     */
    public EmploymentResponse(Boolean status) {
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