package com.payzout.customer.lending.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LoanRecords {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    /**
     * No args constructor for use in serialization
     *
     */
    public LoanRecords() {
    }

    /**
     *
     * @param data
     * @param status
     */
    public LoanRecords(Boolean status, List<Datum> data) {
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

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public class Datum {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("plan_id")
        @Expose
        private Integer planId;
        @SerializedName("user_type")
        @Expose
        private Integer userType;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("bank_id")
        @Expose
        private String bankId;
        @SerializedName("disbursement_amount")
        @Expose
        private Integer disbursementAmount;
        @SerializedName("processing_fee")
        @Expose
        private Integer processingFee;
        @SerializedName("gst_on_processing_fee")
        @Expose
        private Integer gstOnProcessingFee;
        @SerializedName("interest_fee")
        @Expose
        private Integer interestFee;
        @SerializedName("overdue_days")
        @Expose
        private Integer overdueDays;
        @SerializedName("overdue_fee")
        @Expose
        private Integer overdueFee;
        @SerializedName("gst_overdue_fee")
        @Expose
        private Integer gstOverdueFee;
        @SerializedName("disbursement_date")
        @Expose
        private String disbursementDate;
        @SerializedName("last_date_for_repayment")
        @Expose
        private String lastDateForRepayment;
        @SerializedName("maturity_date")
        @Expose
        private String maturityDate;
        @SerializedName("repayment_date")
        @Expose
        private String repaymentDate;
        @SerializedName("is_delay")
        @Expose
        private Integer isDelay;
        @SerializedName("penalty")
        @Expose
        private String penalty;
        @SerializedName("transaction_id")
        @Expose
        private String transactionId;
        @SerializedName("status")
        @Expose
        private Integer status;
        @SerializedName("timestamp")
        @Expose
        private String timestamp;

        /**
         * No args constructor for use in serialization
         *
         */
        public Datum() {
        }

        /**
         *
         * @param isDelay
         * @param overdueFee
         * @param interestFee
         * @param penalty
         * @param userId
         * @param processingFee
         * @param transactionId
         * @param bankId
         * @param gstOverdueFee
         * @param gstOnProcessingFee
         * @param maturityDate
         * @param repaymentDate
         * @param lastDateForRepayment
         * @param overdueDays
         * @param disbursementDate
         * @param planId
         * @param disbursementAmount
         * @param id
         * @param userType
         * @param status
         * @param timestamp
         */
        public Datum(String id, Integer planId, Integer userType, String userId, String bankId, Integer disbursementAmount, Integer processingFee, Integer gstOnProcessingFee, Integer interestFee, Integer overdueDays, Integer overdueFee, Integer gstOverdueFee, String disbursementDate, String lastDateForRepayment, String maturityDate, String repaymentDate, Integer isDelay, String penalty, String transactionId, Integer status, String timestamp) {
            super();
            this.id = id;
            this.planId = planId;
            this.userType = userType;
            this.userId = userId;
            this.bankId = bankId;
            this.disbursementAmount = disbursementAmount;
            this.processingFee = processingFee;
            this.gstOnProcessingFee = gstOnProcessingFee;
            this.interestFee = interestFee;
            this.overdueDays = overdueDays;
            this.overdueFee = overdueFee;
            this.gstOverdueFee = gstOverdueFee;
            this.disbursementDate = disbursementDate;
            this.lastDateForRepayment = lastDateForRepayment;
            this.maturityDate = maturityDate;
            this.repaymentDate = repaymentDate;
            this.isDelay = isDelay;
            this.penalty = penalty;
            this.transactionId = transactionId;
            this.status = status;
            this.timestamp = timestamp;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public Integer getPlanId() {
            return planId;
        }

        public void setPlanId(Integer planId) {
            this.planId = planId;
        }

        public Integer getUserType() {
            return userType;
        }

        public void setUserType(Integer userType) {
            this.userType = userType;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getBankId() {
            return bankId;
        }

        public void setBankId(String bankId) {
            this.bankId = bankId;
        }

        public Integer getDisbursementAmount() {
            return disbursementAmount;
        }

        public void setDisbursementAmount(Integer disbursementAmount) {
            this.disbursementAmount = disbursementAmount;
        }

        public Integer getProcessingFee() {
            return processingFee;
        }

        public void setProcessingFee(Integer processingFee) {
            this.processingFee = processingFee;
        }

        public Integer getGstOnProcessingFee() {
            return gstOnProcessingFee;
        }

        public void setGstOnProcessingFee(Integer gstOnProcessingFee) {
            this.gstOnProcessingFee = gstOnProcessingFee;
        }

        public Integer getInterestFee() {
            return interestFee;
        }

        public void setInterestFee(Integer interestFee) {
            this.interestFee = interestFee;
        }

        public Integer getOverdueDays() {
            return overdueDays;
        }

        public void setOverdueDays(Integer overdueDays) {
            this.overdueDays = overdueDays;
        }

        public Integer getOverdueFee() {
            return overdueFee;
        }

        public void setOverdueFee(Integer overdueFee) {
            this.overdueFee = overdueFee;
        }

        public Integer getGstOverdueFee() {
            return gstOverdueFee;
        }

        public void setGstOverdueFee(Integer gstOverdueFee) {
            this.gstOverdueFee = gstOverdueFee;
        }

        public String getDisbursementDate() {
            return disbursementDate;
        }

        public void setDisbursementDate(String disbursementDate) {
            this.disbursementDate = disbursementDate;
        }

        public String getLastDateForRepayment() {
            return lastDateForRepayment;
        }

        public void setLastDateForRepayment(String lastDateForRepayment) {
            this.lastDateForRepayment = lastDateForRepayment;
        }

        public String getMaturityDate() {
            return maturityDate;
        }

        public void setMaturityDate(String maturityDate) {
            this.maturityDate = maturityDate;
        }

        public String getRepaymentDate() {
            return repaymentDate;
        }

        public void setRepaymentDate(String repaymentDate) {
            this.repaymentDate = repaymentDate;
        }

        public Integer getIsDelay() {
            return isDelay;
        }

        public void setIsDelay(Integer isDelay) {
            this.isDelay = isDelay;
        }

        public String getPenalty() {
            return penalty;
        }

        public void setPenalty(String penalty) {
            this.penalty = penalty;
        }

        public String getTransactionId() {
            return transactionId;
        }

        public void setTransactionId(String transactionId) {
            this.transactionId = transactionId;
        }

        public Integer getStatus() {
            return status;
        }

        public void setStatus(Integer status) {
            this.status = status;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

    }

}
