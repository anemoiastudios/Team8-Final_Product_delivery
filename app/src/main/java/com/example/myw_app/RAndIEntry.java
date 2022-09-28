package com.example.myw_app;

public class RAndIEntry {
    private String rAndITitle;
    private String rAndIDescription;

    public RAndIEntry(String rAndITitle, String rAndIDescription) {
        this.rAndITitle = rAndITitle;
        this.rAndIDescription = rAndIDescription;
    }

    public String getrAndITitle() {
        return rAndITitle;
    }

    public void setrAndITitle(String rAndITitle) {
        this.rAndITitle = rAndITitle;
    }

    public String getrAndIDescription() {
        return rAndIDescription;
    }

    public void setrAndIDescription(String rAndIDescription) {
        this.rAndIDescription = rAndIDescription;
    }

    @Override
    public String toString() {
        return "RAndIEntry{" +
                "rAndITitle='" + rAndITitle + '\'' +
                ", rAndIDescription='" + rAndIDescription + '\'' +
                '}';
    }
}
