package com.payzout.customer.lending.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IfscCheckList {

@SerializedName("BRANCH")
@Expose
private String bRANCH;
@SerializedName("CENTRE")
@Expose
private String cENTRE;
@SerializedName("DISTRICT")
@Expose
private String dISTRICT;
@SerializedName("STATE")
@Expose
private String sTATE;
@SerializedName("ADDRESS")
@Expose
private String aDDRESS;
@SerializedName("CONTACT")
@Expose
private String cONTACT;
@SerializedName("UPI")
@Expose
private Boolean uPI;
@SerializedName("RTGS")
@Expose
private Boolean rTGS;
@SerializedName("CITY")
@Expose
private String cITY;
@SerializedName("NEFT")
@Expose
private Boolean nEFT;
@SerializedName("IMPS")
@Expose
private Boolean iMPS;
@SerializedName("MICR")
@Expose
private String mICR;
@SerializedName("BANK")
@Expose
private String bANK;
@SerializedName("BANKCODE")
@Expose
private String bANKCODE;
@SerializedName("IFSC")
@Expose
private String iFSC;

/**
* No args constructor for use in serialization
*
*/
public IfscCheckList() {
}

/**
*
* @param cITY
* @param dISTRICT
* @param sTATE
* @param cONTACT
* @param bANKCODE
* @param uPI
* @param bANK
* @param nEFT
* @param iMPS
* @param mICR
* @param cENTRE
* @param rTGS
* @param iFSC
* @param aDDRESS
* @param bRANCH
*/
public IfscCheckList(String bRANCH, String cENTRE, String dISTRICT, String sTATE, String aDDRESS, String cONTACT, Boolean uPI, Boolean rTGS, String cITY, Boolean nEFT, Boolean iMPS, String mICR, String bANK, String bANKCODE, String iFSC) {
super();
this.bRANCH = bRANCH;
this.cENTRE = cENTRE;
this.dISTRICT = dISTRICT;
this.sTATE = sTATE;
this.aDDRESS = aDDRESS;
this.cONTACT = cONTACT;
this.uPI = uPI;
this.rTGS = rTGS;
this.cITY = cITY;
this.nEFT = nEFT;
this.iMPS = iMPS;
this.mICR = mICR;
this.bANK = bANK;
this.bANKCODE = bANKCODE;
this.iFSC = iFSC;
}

public String getBRANCH() {
return bRANCH;
}

public void setBRANCH(String bRANCH) {
this.bRANCH = bRANCH;
}

public String getCENTRE() {
return cENTRE;
}

public void setCENTRE(String cENTRE) {
this.cENTRE = cENTRE;
}

public String getDISTRICT() {
return dISTRICT;
}

public void setDISTRICT(String dISTRICT) {
this.dISTRICT = dISTRICT;
}

public String getSTATE() {
return sTATE;
}

public void setSTATE(String sTATE) {
this.sTATE = sTATE;
}

public String getADDRESS() {
return aDDRESS;
}

public void setADDRESS(String aDDRESS) {
this.aDDRESS = aDDRESS;
}

public String getCONTACT() {
return cONTACT;
}

public void setCONTACT(String cONTACT) {
this.cONTACT = cONTACT;
}

public Boolean getUPI() {
return uPI;
}

public void setUPI(Boolean uPI) {
this.uPI = uPI;
}

public Boolean getRTGS() {
return rTGS;
}

public void setRTGS(Boolean rTGS) {
this.rTGS = rTGS;
}

public String getCITY() {
return cITY;
}

public void setCITY(String cITY) {
this.cITY = cITY;
}

public Boolean getNEFT() {
return nEFT;
}

public void setNEFT(Boolean nEFT) {
this.nEFT = nEFT;
}

public Boolean getIMPS() {
return iMPS;
}

public void setIMPS(Boolean iMPS) {
this.iMPS = iMPS;
}

public String getMICR() {
return mICR;
}

public void setMICR(String mICR) {
this.mICR = mICR;
}

public String getBANK() {
return bANK;
}

public void setBANK(String bANK) {
this.bANK = bANK;
}

public String getBANKCODE() {
return bANKCODE;
}

public void setBANKCODE(String bANKCODE) {
this.bANKCODE = bANKCODE;
}

public String getIFSC() {
return iFSC;
}

public void setIFSC(String iFSC) {
this.iFSC = iFSC;
}

}