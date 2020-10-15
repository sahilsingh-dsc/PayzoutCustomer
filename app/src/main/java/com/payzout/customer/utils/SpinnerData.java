package com.payzout.customer.utils;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpinnerData {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    /**
     * No args constructor for use in serialization
     */
    public SpinnerData() {
    }

    /**
     * @param data
     * @param status
     */
    public SpinnerData(Boolean status, List<Datum> data) {
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

        @SerializedName("state_id")
        @Expose
        private Integer stateId;
        @SerializedName("country_id")
        @Expose
        private Integer countryId;
        @SerializedName("state_name")
        @Expose
        private String stateName;

        /**
         * No args constructor for use in serialization
         *
         */
        public Datum() {
        }

        /**
         *
         * @param stateName
         * @param stateId
         * @param countryId
         */
        public Datum(Integer stateId, Integer countryId, String stateName) {
            super();
            this.stateId = stateId;
            this.countryId = countryId;
            this.stateName = stateName;
        }

        public Integer getStateId() {
            return stateId;
        }

        public void setStateId(Integer stateId) {
            this.stateId = stateId;
        }

        public Integer getCountryId() {
            return countryId;
        }

        public void setCountryId(Integer countryId) {
            this.countryId = countryId;
        }

        public String getStateName() {
            return stateName;
        }

        public void setStateName(String stateName) {
            this.stateName = stateName;
        }

    }

}