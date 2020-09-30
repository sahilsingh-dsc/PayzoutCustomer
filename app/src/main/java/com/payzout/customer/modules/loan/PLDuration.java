package com.payzout.customer.modules.loan;

public class PLDuration {

    public static final int LOAN_STATUS_DURATION_LOCKED = 0;
    public static final int LOAN_STATUS_DURATION_OPENED = 1;
    public static final int DURATION_TYPE_DAYS = 1;
    public static final int DURATION_TYPE_MONTHS = 2;

    private int id;
    private int duration;
    private int type;
    private int status;

    public PLDuration() {
    }

    public PLDuration(int id, int duration, int type, int status) {
        this.id = id;
        this.duration = duration;
        this.type = type;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
