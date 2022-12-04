package com.example.mindyawellnessdraft6;

public class ProviderAppointment {
    private String appointmentTitle;
    private String appointmentDate;
    private String customerName;
    private String appointmentCustomerUid;

    public ProviderAppointment(String appointmentTitle, String appointmentDate, String customerName) {
        this.appointmentTitle = appointmentTitle;
        this.appointmentDate = appointmentDate;
        this.customerName = customerName;
    }

    public ProviderAppointment(String appointmentTitle, String appointmentDate, String customerName, String appointmentCustomerUid) {
        this.appointmentTitle = appointmentTitle;
        this.appointmentDate = appointmentDate;
        this.customerName = customerName;
        this.appointmentCustomerUid = appointmentCustomerUid;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAppointmentCustomerUid() {
        return appointmentCustomerUid;
    }

    public void setAppointmentCustomerUid(String appointmentCustomerUid) {
        this.appointmentCustomerUid = appointmentCustomerUid;
    }
}
