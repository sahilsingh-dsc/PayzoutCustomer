package com.payzout.customer.lending.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDL {
    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     *
     */
    public UserDL() {
    }

    /**
     *
     * @param message
     */
    public UserDL(String message) {
        super();
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
