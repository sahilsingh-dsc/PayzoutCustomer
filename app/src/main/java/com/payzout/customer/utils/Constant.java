package com.payzout.customer.utils;

public class Constant {
    public static final String AUTH_SENDING_CODE = "Sending code...";
    public static final String AUTH_RESENDING_CODE = "Resending code...";
    public static final String AUTH_AUTO_VERIFY = "Waiting for auto verification...";

    public static final int KYC_BAN = 0;
    public static final int KYC_NONE = 1;
    public static final int KYC_PENDING = 2;
    public static final int KYC_APPROVED = 3;
    public static final int KYC_REJECTED = 4;
    public static final int KYC_ONGOING_LOAN = 5;

    public static final int PHOTO_SELFIE = 1;
    public static final int PHOTO_PAN = 2;
    public static final int PHOTO_AF = 3;
    public static final int PHOTO_AB = 4;
    public static final int PHOTO_DL = 5;
    public static final int PDF_BS = 6;
    public static final int REF_CONTACT_1 = 1;
    public static final int REF_CONTACT_2 = 2;

    public static final int HTTP_RESPONSE_SUCCESS = 200;
    public static final int HTTP_RESPONSE_BAD_REQUEST = 400;
}
