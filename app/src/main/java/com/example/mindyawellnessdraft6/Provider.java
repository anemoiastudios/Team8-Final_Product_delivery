package com.example.mindyawellnessdraft6;

// UNFINISHED

public class Provider {
    private String providerName;
    private String therapistType;
    private String providerUid;

    /*
    public Provider(String providerName, String therapistType) {
        this.providerName = providerName;
        this.therapistType = therapistType;
    }

     */

    public Provider(String providerName, String therapistType, String providerUid) {
        this.providerName = providerName;
        this.therapistType = therapistType;
        this.providerUid = providerUid;
    }

    public String getProviderName() {
        return providerName;
    }

    public void setProviderName(String providerName) {
        this.providerName = providerName;
    }

    public String getTherapistType() {
        return therapistType;
    }

    public void setTherapistType(String therapistType) {
        this.therapistType = therapistType;
    }

    public String getProviderUid() {
        return providerUid;
    }

    public void setProviderUid(String providerUid) {
        this.providerUid = providerUid;
    }
}
