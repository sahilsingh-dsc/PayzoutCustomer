package com.payzout.customer.lending.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoanOfferResponse {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("amount")
    @Expose
    private Integer amount;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("interest_fee")
    @Expose
    private Integer interestFee;
    @SerializedName("processing_fee")
    @Expose
    private Integer processingFee;
    @SerializedName("gst_processing")
    @Expose
    private Float gstProcessing;
    @SerializedName("disbursal_amount")
    @Expose
    private Float disbursalAmount;
    @SerializedName("repayment_date")
    @Expose
    private String repaymentDate;

    /**
     * No args constructor for use in serialization
     */
    public LoanOfferResponse() {
    }

    /**
     * @param duration
     * @param disbursalAmount
     * @param amount
     * @param interestFee
     * @param repaymentDate
     * @param gstProcessing
     * @param id
     * @param processingFee
     */
    public LoanOfferResponse(Integer id, Integer amount, Integer duration, Integer interestFee, Integer processingFee, Float gstProcessing, Float disbursalAmount, String repaymentDate) {
        super();
        this.id = id;
        this.amount = amount;
        this.duration = duration;
        this.interestFee = interestFee;
        this.processingFee = processingFee;
        this.gstProcessing = gstProcessing;
        this.disbursalAmount = disbursalAmount;
        this.repaymentDate = repaymentDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getInterestFee() {
        return interestFee;
    }

    public void setInterestFee(Integer interestFee) {
        this.interestFee = interestFee;
    }

    public Integer getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(Integer processingFee) {
        this.processingFee = processingFee;
    }

    public Float getGstProcessing() {
        return gstProcessing;
    }

    public void setGstProcessing(Float gstProcessing) {
        this.gstProcessing = gstProcessing;
    }

    public Float getDisbursalAmount() {
        return disbursalAmount;
    }

    public void setDisbursalAmount(Float disbursalAmount) {
        this.disbursalAmount = disbursalAmount;
    }

    public String getRepaymentDate() {
        return repaymentDate;
    }

    public void setRepaymentDate(String repaymentDate) {
        this.repaymentDate = repaymentDate;
    }

}