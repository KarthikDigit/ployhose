package com.polyhose.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Attendance implements Serializable {

    @SerializedName("attendanceId")
    @Expose
    private Integer attendanceId;
    @SerializedName("gpsLat")
    @Expose
    private String gpsLat;
    @SerializedName("gpsLong")
    @Expose
    private String gpsLong;
    @SerializedName("place")
    @Expose
    private String place;
    @SerializedName("inDate")
    @Expose
    private String inDate;
    @SerializedName("inTime")
    @Expose
    private String inTime;
    @SerializedName("outTime")
    @Expose
    private String outTime;
    private final static long serialVersionUID = 6528833713304813302L;

    public Integer getAttendanceId() {
        return attendanceId;
    }

    public void setAttendanceId(Integer attendanceId) {
        this.attendanceId = attendanceId;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLong() {
        return gpsLong;
    }

    public void setGpsLong(String gpsLong) {
        this.gpsLong = gpsLong;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getInDate() {
        return inDate;
    }

    public void setInDate(String inDate) {
        this.inDate = inDate;
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
