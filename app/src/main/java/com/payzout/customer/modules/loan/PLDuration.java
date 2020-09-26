package com.payzout.customer.modules.loan;

public class PLDuration {
    private int id;
    private int duration;

    public PLDuration() {
    }

    public PLDuration(int id, int duration) {
        this.id = id;
        this.duration = duration;
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
}
