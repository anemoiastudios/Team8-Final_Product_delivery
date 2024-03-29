package com.example.mindyawellnessdraft6;

public class CustomerAppointment {
    private String appointmentTitle;
    private String appointmentDate;
    private String appointmentProvider;
    private String appointmentProviderUid;

    public CustomerAppointment(String appointmentTitle, String appointmentDate, String appointmentProvider, String appointmentProviderUid) {
        this.appointmentTitle = appointmentTitle;
        this.appointmentDate = appointmentDate;
        this.appointmentProvider = appointmentProvider;
        this.appointmentProviderUid = appointmentProviderUid;
    }

    public CustomerAppointment(String appointmentTitle, String appointmentDate, String appointmentProvider) {
        this.appointmentTitle = appointmentTitle;
        this.appointmentDate = appointmentDate;
        this.appointmentProvider = appointmentProvider;
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

    public String getAppointmentProvider() {
        return appointmentProvider;
    }

    public void setAppointmentProvider(String appointmentProvider) {
        this.appointmentProvider = appointmentProvider;
    }

    public String getAppointmentProviderUid() {
        return appointmentProviderUid;
    }

    public void setAppointmentProviderUid(String appointmentProviderUid) {
        this.appointmentProviderUid = appointmentProviderUid;
    }
}
