package com.example.mindyawellnessdraft6;

public class Customer {
    private String customerName;
    private String customerUid;

    public Customer(String customerName, String customerUid) {
        this.customerName = customerName;
        this.customerUid = customerUid;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerUid() {
        return customerUid;
    }

    public void setCustomerUid(String customerUid) {
        this.customerUid = customerUid;
    }

    @Override
    public String toString() {
        return customerName;
    }
}
