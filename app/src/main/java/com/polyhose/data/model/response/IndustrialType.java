package com.polyhose.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class IndustrialType implements Serializable {

    @SerializedName("industrialID")
    @Expose
    private Integer industrialID;
    @SerializedName("industrialName")
    @Expose
    private String industrialName;
    private final static long serialVersionUID = -1768910945321268055L;

    public Integer getIndustrialID() {
        return industrialID;
    }

    public void setIndustrialID(Integer industrialID) {
        this.industrialID = industrialID;
    }

    public String getIndustrialName() {
        return industrialName;
    }

    public void setIndustrialName(String industrialName) {
        this.industrialName = industrialName;
    }

    @Override
    public String toString() {
        return industrialName;
    }
}
