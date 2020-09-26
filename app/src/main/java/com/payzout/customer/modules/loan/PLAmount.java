package com.payzout.customer.modules.loan;

public class PLAmount {
    private int id;
    private int amount;

    public PLAmount() {
    }

    public PLAmount(int id, int amount) {
        this.id = id;
        this.amount = amount;
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
}
