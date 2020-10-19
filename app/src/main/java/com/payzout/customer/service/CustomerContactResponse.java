package com.payzout.customer.service;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CustomerContactResponse {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("type")
    @Expose
    private Integer type;

    /**
     * No args constructor for use in serialization
     */
    public CustomerContactResponse() {
    }

    /**
     * @param phone
     * @param name
     * @param id
     * @param type
     * @param userId
     * @param timestamp
     */
    public CustomerContactResponse(String id, String userId, String name, String phone, String timestamp, Integer type) {
        super();
        this.id = id;
        this.userId = userId;
        this.name = name;
        this.phone = phone;
        this.timestamp = timestamp;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

}