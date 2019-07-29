package com.polyhose.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Customers implements Serializable {

    @SerializedName("customer_ID")
    @Expose
    private Integer customerID;
    @SerializedName("customer_name")
    @Expose
    private String customerName;
    @SerializedName("customer_type")
    @Expose
    private String customerType;
    @SerializedName("industry")
    @Expose
    private String industry;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customers)) return false;

        Customers customers = (Customers) o;

        if (customerID != null ? !customerID.equals(customers.customerID) : customers.customerID != null)
            return false;
        return customerName != null ? customerName.equals(customers.customerName) : customers.customerName == null;
    }

    @Override
    public int hashCode() {
        int result = customerID != null ? customerID.hashCode() : 0;
        result = 31 * result + (customerName != null ? customerName.hashCode() : 0);
        return result;
    }

    private final static long serialVersionUID = 2516522577209472437L;

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    @Override
    public String toString() {
        return customerName;
    }
}
