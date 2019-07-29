package com.polyhose.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ContactPerson implements Serializable {

    @SerializedName("contact_ID")
    @Expose
    private Integer contactID;
    @SerializedName("contact_person")
    @Expose
    private String contactPerson;
    @SerializedName("customer_Department")
    @Expose
    private String customerDepartment;
    @SerializedName("contact_mobile")
    @Expose
    private String contactMobile;
    @SerializedName("contact_jobposition")
    @Expose
    private String contactJobposition;
    @SerializedName("contact_email")
    @Expose
    private String contactEmail;
    @SerializedName("contact_Status")
    @Expose
    private Integer contactStatus;
    @SerializedName("contact_BusinessCard")
    @Expose
    private String contactBusinessCard;
    @SerializedName("lastmodifyby")
    @Expose
    private String lastmodifyby;
    @SerializedName("lastmodifytime")
    @Expose
    private String lastmodifytime;
    private final static long serialVersionUID = -416788271834430515L;

    public Integer getContactID() {
        return contactID;
    }

    public void setContactID(Integer contactID) {
        this.contactID = contactID;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCustomerDepartment() {
        return customerDepartment;
    }

    public void setCustomerDepartment(String customerDepartment) {
        this.customerDepartment = customerDepartment;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactJobposition() {
        return contactJobposition;
    }

    public void setContactJobposition(String contactJobposition) {
        this.contactJobposition = contactJobposition;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public Integer getContactStatus() {
        return contactStatus;
    }

    public void setContactStatus(Integer contactStatus) {
        this.contactStatus = contactStatus;
    }

    public String getContactBusinessCard() {
        return contactBusinessCard;
    }

    public void setContactBusinessCard(String contactBusinessCard) {
        this.contactBusinessCard = contactBusinessCard;
    }

    public String getLastmodifyby() {
        return lastmodifyby;
    }

    public void setLastmodifyby(String lastmodifyby) {
        this.lastmodifyby = lastmodifyby;
    }

    public String getLastmodifytime() {
        return lastmodifytime;
    }

    public void setLastmodifytime(String lastmodifytime) {
        this.lastmodifytime = lastmodifytime;
    }

    @Override
    public String toString() {
        return  contactPerson;
    }
}
