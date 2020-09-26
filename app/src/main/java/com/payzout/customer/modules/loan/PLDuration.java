package com.payzout.customer.modules.loan;

public class PLDuration {
    private int id;
    private String duration;

    public PLDuration() {
    }


    public PLDuration(int id, String duration) {
        this.id = id;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }
}
