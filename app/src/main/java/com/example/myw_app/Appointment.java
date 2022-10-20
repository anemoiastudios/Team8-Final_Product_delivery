package com.example.myw_app;

public class Appointment {

    private String dayDateYear;
    private String time;
    private String name;
    private String location;

    public Appointment(String dayDateYear, String time, String name, String location) {
        this.dayDateYear = dayDateYear;
        this.time = time;
        this.name = name;
        this.location = location;
    }

    public String getDayDateYear() {
        return dayDateYear;
    }

    public void setDayDateYear(String dayDateYear) {
        this.dayDateYear = dayDateYear;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
