package com.polyhose.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class States implements Serializable {

    @SerializedName("stateID")
    @Expose
    private Integer stateID;
    @SerializedName("stateName")
    @Expose
    private String stateName;
    @SerializedName("region_ID")
    @Expose
    private Integer regionID;
    private final static long serialVersionUID = 8960318462474050354L;

    public Integer getStateID() {
        return stateID;
    }

    public void setStateID(Integer stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Integer getRegionID() {
        return regionID;
    }

    public void setRegionID(Integer regionID) {
        this.regionID = regionID;
    }

    @Override
    public String toString() {
        return stateName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof States)) return false;

        States states = (States) o;

        return stateName != null ? stateName.equals(states.stateName) : states.stateName == null;
    }

    @Override
    public int hashCode() {
        return stateName != null ? stateName.hashCode() : 0;
    }
}
