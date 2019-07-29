package com.polyhose.data.model.request;

import com.polyhose.data.model.response.TaskAdd;

import java.util.List;

public class TaskPost {

    private TaskAdd.Data.Dealer dealer;

    private List<TaskAdd.Data.Perform> performs;

    private String teskDate;

    private String remark;

    private double latitude;

    private double longtitude;

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public TaskAdd.Data.Dealer getDealer() {
        return dealer;
    }

    public void setDealer(TaskAdd.Data.Dealer dealer) {
        this.dealer = dealer;
    }

    public List<TaskAdd.Data.Perform> getPerforms() {
        return performs;
    }

    public void setPerforms(List<TaskAdd.Data.Perform> performs) {
        this.performs = performs;
    }

    public String getTeskDate() {
        return teskDate;
    }

    public void setTeskDate(String teskDate) {
        this.teskDate = teskDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
