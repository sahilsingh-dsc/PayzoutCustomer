package com.payzout.customer.lending.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddCustomerReference {

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
    public AddCustomerReference() {
    }

    /**
     *
     * @param data
     * @param status
     */
    public AddCustomerReference(Boolean status, Data data) {
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

}


class Data {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("user_id")
    @Expose
    private String userId;
    @SerializedName("reference_type1")
    @Expose
    private String referenceType1;
    @SerializedName("name1")
    @Expose
    private String name1;
    @SerializedName("phone_no1")
    @Expose
    private String phoneNo1;
    @SerializedName("reference_type2")
    @Expose
    private String referenceType2;
    @SerializedName("name2")
    @Expose
    private String name2;
    @SerializedName("phone_no2")
    @Expose
    private String phoneNo2;
    @SerializedName("timestamp")
    @Expose
    private String timestamp;
    @SerializedName("type")
    @Expose
    private Integer type;

    /**
     * No args constructor for use in serialization
     */
    public Data() {
    }

    /**
     * @param phoneNo1
     * @param phoneNo2
     * @param referenceType2
     * @param id
     * @param referenceType1
     * @param name2
     * @param type
     * @param userId
     * @param name1
     * @param timestamp
     */
    public Data(String id, String userId, String referenceType1, String name1, String phoneNo1, String referenceType2, String name2, String phoneNo2, String timestamp, Integer type) {
        super();
        this.id = id;
        this.userId = userId;
        this.referenceType1 = referenceType1;
        this.name1 = name1;
        this.phoneNo1 = phoneNo1;
        this.referenceType2 = referenceType2;
        this.name2 = name2;
        this.phoneNo2 = phoneNo2;
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

    public String getReferenceType1() {
        return referenceType1;
    }

    public void setReferenceType1(String referenceType1) {
        this.referenceType1 = referenceType1;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getPhoneNo1() {
        return phoneNo1;
    }

    public void setPhoneNo1(String phoneNo1) {
        this.phoneNo1 = phoneNo1;
    }

    public String getReferenceType2() {
        return referenceType2;
    }

    public void setReferenceType2(String referenceType2) {
        this.referenceType2 = referenceType2;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    public String getPhoneNo2() {
        return phoneNo2;
    }

    public void setPhoneNo2(String phoneNo2) {
        this.phoneNo2 = phoneNo2;
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