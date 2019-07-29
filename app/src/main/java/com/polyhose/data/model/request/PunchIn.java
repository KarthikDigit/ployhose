package com.polyhose.data.model.request;

public class PunchIn {

    private Integer userId;

    private double GPSLat;

    private double GPSLong;

    private String place;

    private String INDate;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public double getGPSLat() {
        return GPSLat;
    }

    public void setGPSLat(double GPSLat) {
        this.GPSLat = GPSLat;
    }

    public double getGPSLong() {
        return GPSLong;
    }

    public void setGPSLong(double GPSLong) {
        this.GPSLong = GPSLong;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getINDate() {
        return INDate;
    }

    public void setINDate(String INDate) {
        this.INDate = INDate;
    }
}
