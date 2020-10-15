package com.payzout.customer.lending.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KycBankList {

@SerializedName("status")
@Expose
private Boolean status;
@SerializedName("message")
@Expose
private String message;

/**
* No args constructor for use in serialization
*
*/
public KycBankList() {
}

/**
*
* @param message
* @param status
*/
public KycBankList(Boolean status, String message) {
super();
this.status = status;
this.message = message;
}

public Boolean getStatus() {
return status;
}

public void setStatus(Boolean status) {
this.status = status;
}

public String getMessage() {
return message;
}

public void setMessage(String message) {
this.message = message;
}

}