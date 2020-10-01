
package com.payzout.customer.modules.kyc.basic;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BasicInfo {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private BasicInfoResponse basicInfoResponse;

    /**
     * No args constructor for use in serialization
     * 
     */
    public BasicInfo() {
    }

    /**
     * 
     * @param basicInfoResponse
     * @param status
     */
    public BasicInfo(Boolean status, BasicInfoResponse basicInfoResponse) {
        super();
        this.status = status;
        this.basicInfoResponse = basicInfoResponse;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public BasicInfoResponse getBasicInfoResponse() {
        return basicInfoResponse;
    }

    public void setBasicInfoResponse(BasicInfoResponse basicInfoResponse) {
        this.basicInfoResponse = basicInfoResponse;
    }

}
