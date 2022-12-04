package com.example.mindyawellnessdraft6;

import java.util.ArrayList;

public class Appointment {
    private String appointmentTitle;
    private String appointmentDate;
    private String appointmentTime;
    private String appointmentDescription;
    private String customerUid;
    private String providerUid;
    private String providerAccepted;
    private String appointmentId;


    public Appointment(String appointmentTitle, String appointmentDate, String appointmentTime, String appointmentDescription, String customerUid, String providerUid, String appointmentId) {
        this.appointmentTitle = appointmentTitle;
        this.appointmentDate = appointmentDate;
        this.appointmentTime = appointmentTime;
        this.appointmentDescription = appointmentDescription;
        this.customerUid = customerUid;
        this.providerUid = providerUid;
        providerAccepted = "0";
        this.appointmentId = appointmentId;
    }

    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getAppointmentDescription() {
        return appointmentDescription;
    }

    public void setAppointmentDescription(String appointmentDescription) {
        this.appointmentDescription = appointmentDescription;
    }

    public String getCustomerUid() {
        return customerUid;
    }

    public void setCustomerUid(String customerUid) {
        this.customerUid = customerUid;
    }


    public String getProviderUid() {
        return providerUid;
    }

    public void setProviderUid(String providerUid) {
        this.providerUid = providerUid;
    }


    public String getAppointmentTime() {
        return appointmentTime;
    }

    public void setAppointmentTime(String appointmentTime) {
        this.appointmentTime = appointmentTime;
    }

    public String getProviderAccepted() {
        return providerAccepted;
    }

    public void setProviderAccepted(String providerAccepted) {
        this.providerAccepted = providerAccepted;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }
}
