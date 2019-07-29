package com.polyhose.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CompanyType implements Serializable {

    @SerializedName("companyId")
    @Expose
    private Integer companyId;
    @SerializedName("companyType")
    @Expose
    private String companyType;
    private final static long serialVersionUID = 7430948622321864398L;

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    @Override
    public String toString() {
        return companyType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CompanyType)) return false;

        CompanyType that = (CompanyType) o;

        return companyId != null ? companyId.equals(that.companyId) : that.companyId == null;
    }

    @Override
    public int hashCode() {
        return companyId != null ? companyId.hashCode() : 0;
    }
}
