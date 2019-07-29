package com.polyhose.data.model.request;

public class PunchOut {

    private Integer userId;

    private double GPSLat;

    private double GPSLong;

    private String OUTDate;

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


    public String getOUTDate() {
        return OUTDate;
    }

    public void setOUTDate(String OUTDate) {
        this.OUTDate = OUTDate;
    }
}
