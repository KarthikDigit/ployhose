package com.aclocationtrack.dashboard.myattendance;

public class Attendance {

    private String date, inTime, outTime;

    public Attendance(String date, String inTime, String outTime) {
        this.date = date;
        this.inTime = inTime;
        this.outTime = outTime;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getOutTime() {
        return outTime;
    }

    public void setOutTime(String outTime) {
        this.outTime = outTime;
    }
}
