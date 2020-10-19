package com.payzout.customer.lending.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserSelfie {

    @SerializedName("message")
    @Expose
    private String message;

    /**
     * No args constructor for use in serialization
     */
    public UserSelfie() {
    }

    /**
     * @param message
     */
    public UserSelfie(String message) {
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