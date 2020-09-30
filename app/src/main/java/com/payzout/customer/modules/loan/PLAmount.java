package com.payzout.customer.modules.loan;

public class PLAmount {

    public static final int LOAN_STATUS_AMOUNT_LOCKED = 0;
    public static final int LOAN_STATUS_AMOUNT_OPENED = 1;

    private int id;
    private int amount;
    private int status;

    public PLAmount() {
    }

    public PLAmount(int id, int amount, int status) {
        this.id = id;
        this.amount = amount;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
