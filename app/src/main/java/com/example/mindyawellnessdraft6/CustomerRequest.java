package com.example.mindyawellnessdraft6;

public class CustomerRequest {
    private String customerName;
    private String meetingDate;

    public CustomerRequest(String customerName, String meetingDate) {
        this.customerName = customerName;
        this.meetingDate = meetingDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getMeetingDate() {
        return meetingDate;
    }

    public void setMeetingDate(String meetingDate) {
        this.meetingDate = meetingDate;
    }
}
