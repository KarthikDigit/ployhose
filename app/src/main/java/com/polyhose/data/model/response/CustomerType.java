package com.polyhose.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerType implements Serializable {

    @SerializedName("customerType_id")
    @Expose
    private Integer customerTypeId;
    @SerializedName("customerType")
    @Expose
    private String customerType;
    private final static long serialVersionUID = 1163171222917261837L;

    public Integer getCustomerTypeId() {
        return customerTypeId;
    }

    public void setCustomerTypeId(Integer customerTypeId) {
        this.customerTypeId = customerTypeId;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    @Override
    public String toString() {
        return customerType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CustomerType)) return false;

        CustomerType that = (CustomerType) o;

        return customerTypeId != null ? customerTypeId.equals(that.customerTypeId) : that.customerTypeId == null;
    }

    @Override
    public int hashCode() {
        return customerTypeId != null ? customerTypeId.hashCode() : 0;
    }
}
