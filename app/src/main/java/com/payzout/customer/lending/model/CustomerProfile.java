package com.payzout.customer.lending.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerProfile {

@SerializedName("status")
@Expose
private Boolean status;
@SerializedName("data")
@Expose
private Data data;

/**
* No args constructor for use in serialization
*
*/
public CustomerProfile() {
}

/**
*
* @param data
* @param status
*/
public CustomerProfile(Boolean status, Data data) {
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

public Data getData() {
return data;
}

public void setData(Data data) {
this.data = data;
}


    public class Data {

        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("credit_points")
        @Expose
        private Integer creditPoints;
        @SerializedName("phone_number")
        @Expose
        private String phoneNumber;
        @SerializedName("full_name")
        @Expose
        private String fullName;

        /**
         * No args constructor for use in serialization
         *
         */
        public Data() {
        }

        /**
         *
         * @param creditPoints
         * @param phoneNumber
         * @param fullName
         * @param userId
         * @param email
         */
        public Data(String userId, String email, Integer creditPoints, String phoneNumber, String fullName) {
            super();
            this.userId = userId;
            this.email = email;
            this.creditPoints = creditPoints;
            this.phoneNumber = phoneNumber;
            this.fullName = fullName;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Integer getCreditPoints() {
            return creditPoints;
        }

        public void setCreditPoints(Integer creditPoints) {
            this.creditPoints = creditPoints;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getFullName() {
            return fullName;
        }

        public void setFullName(String fullName) {
            this.fullName = fullName;
        }

    }

}