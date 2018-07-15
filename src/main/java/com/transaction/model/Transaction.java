package com.transaction.model;

import java.io.Serializable;

/**
 * Created by mumarm45 on 13/07/2018.
 */
public class Transaction implements Serializable {

    private static final long serialVersionUID = 4782612550980962226L;

    private double amount;
    private long timestamp;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Statistic{" +
                "amount=" + amount +
                ", timestamp=" + timestamp +
                '}';
    }
}
