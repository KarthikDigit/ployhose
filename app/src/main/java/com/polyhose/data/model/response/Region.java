package com.polyhose.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Region implements Serializable {

    @SerializedName("region_ID")
    @Expose
    private Integer regionID;
    @SerializedName("region_Name")
    @Expose
    private String regionName;
    @SerializedName("status")
    @Expose
    private Integer status;
    private final static long serialVersionUID = 3541047618266499912L;

    public Integer getRegionID() {
        return regionID;
    }

    public void setRegionID(Integer regionID) {
        this.regionID = regionID;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return regionName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Region)) return false;

        Region region = (Region) o;

        return regionID != null ? regionID.equals(region.regionID) : region.regionID == null;
    }

    @Override
    public int hashCode() {
        return regionID != null ? regionID.hashCode() : 0;
    }
}
