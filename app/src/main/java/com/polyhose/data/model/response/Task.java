package com.polyhose.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Task implements Serializable {


    //    transient List<Customer> customers;
    List<Customer> customers;


    @SerializedName("taskId")
    @Expose
    private Integer taskId;
    @SerializedName("customerId")
    @Expose
    private Integer customerId;
    @SerializedName("followup")
    @Expose
    private String followup;
    @SerializedName("followupNotes")
    @Expose
    private String followupNotes;
    @SerializedName("complaints")
    @Expose
    private String complaints;
    @SerializedName("others")
    @Expose
    private String others;
    @SerializedName("demo")
    @Expose
    private String demo;
    @SerializedName("allocatedFlag")
    @Expose
    private Integer allocatedFlag;
    @SerializedName("orderFlag")
    @Expose
    private Integer orderFlag;
    @SerializedName("followupFlag")
    @Expose
    private Integer followupFlag;
    @SerializedName("complaintsFlag")
    @Expose
    private Integer complaintsFlag;
    @SerializedName("othersFlag")
    @Expose
    private Integer othersFlag;
    @SerializedName("demoFlag")
    @Expose
    private Integer demoFlag;
    @SerializedName("tour")
    @Expose
    private String tour;
    @SerializedName("taskDate")
    @Expose
    private String taskDate;
    @SerializedName("createdBy")
    @Expose
    private Integer createdBy;
    @SerializedName("createdTime")
    @Expose
    private String createdTime;
    @SerializedName("allocatedTo")
    @Expose
    private Integer allocatedTo;
    @SerializedName("taskStatus")
    @Expose
    private Integer taskStatus;
    @SerializedName("lastModifiedDate")
    @Expose
    private String lastModifiedDate;
    @SerializedName("lastModifiedUser")
    @Expose
    private Object lastModifiedUser;
    @SerializedName("contactId")
    @Expose
    private Integer contactId;
    @SerializedName("isActive")
    @Expose
    private Integer isActive;
    @SerializedName("orderid")
    @Expose
    private Object orderid;
    @SerializedName("regionid")
    @Expose
    private Integer regionid;
    @SerializedName("isRoleid")
    @Expose
    private Object isRoleid;
    @SerializedName("gpsLat")
    @Expose
    private String gpsLat;
    @SerializedName("gpsLong")
    @Expose
    private String gpsLong;
    @SerializedName("place")
    @Expose
    private Object place;
    @SerializedName("apiKey")
    @Expose
    private Object apiKey;
    @SerializedName("orders")
    @Expose
    private Object orders;
    @SerializedName("paymentOutstanding")
    @Expose
    private Object paymentOutstanding;
    @SerializedName("cforms")
    @Expose
    private Object cforms;
    @SerializedName("chequeStock")
    @Expose
    private Object chequeStock;
    @SerializedName("ordersdata")
    @Expose
    private Object ordersdata;
    @SerializedName("paymentdata")
    @Expose
    private Object paymentdata;
    @SerializedName("checkdata")
    @Expose
    private Object checkdata;
    private final static long serialVersionUID = -5975609338637502678L;


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof Task)) {
            return false;
        }

        return taskId.equals(((Task) obj).getTaskId());
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + (this.taskId != null ? this.taskId.hashCode() : 0);
        return hash;
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (!(o instanceof Task)) return false;
//
//        Task task = (Task) o;
//
//        return taskId != null ? taskId.equals(task.taskId) : task.taskId == null;
//    }
//
//    @Override
//    public int hashCode() {
//        return taskId != null ? taskId.hashCode() : 0;
//    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public String getFollowup() {
        return followup;
    }

    public void setFollowup(String followup) {
        this.followup = followup;
    }

    public String getFollowupNotes() {
        return followupNotes;
    }

    public void setFollowupNotes(String followupNotes) {
        this.followupNotes = followupNotes;
    }

    public String getComplaints() {
        return complaints;
    }

    public void setComplaints(String complaints) {
        this.complaints = complaints;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getDemo() {
        return demo;
    }

    public void setDemo(String demo) {
        this.demo = demo;
    }

    public Integer getAllocatedFlag() {
        return allocatedFlag;
    }

    public void setAllocatedFlag(Integer allocatedFlag) {
        this.allocatedFlag = allocatedFlag;
    }

    public Integer getOrderFlag() {
        return orderFlag;
    }

    public void setOrderFlag(Integer orderFlag) {
        this.orderFlag = orderFlag;
    }

    public Integer getFollowupFlag() {
        return followupFlag;
    }

    public void setFollowupFlag(Integer followupFlag) {
        this.followupFlag = followupFlag;
    }

    public Integer getComplaintsFlag() {
        return complaintsFlag;
    }

    public void setComplaintsFlag(Integer complaintsFlag) {
        this.complaintsFlag = complaintsFlag;
    }

    public Integer getOthersFlag() {
        return othersFlag;
    }

    public void setOthersFlag(Integer othersFlag) {
        this.othersFlag = othersFlag;
    }

    public Integer getDemoFlag() {
        return demoFlag;
    }

    public void setDemoFlag(Integer demoFlag) {
        this.demoFlag = demoFlag;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }

    public String getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(String taskDate) {
        this.taskDate = taskDate;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getAllocatedTo() {
        return allocatedTo;
    }

    public void setAllocatedTo(Integer allocatedTo) {
        this.allocatedTo = allocatedTo;
    }

    public Integer getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(Integer taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(String lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public Object getLastModifiedUser() {
        return lastModifiedUser;
    }

    public void setLastModifiedUser(Object lastModifiedUser) {
        this.lastModifiedUser = lastModifiedUser;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getIsActive() {
        return isActive;
    }

    public void setIsActive(Integer isActive) {
        this.isActive = isActive;
    }

    public Object getOrderid() {
        return orderid;
    }

    public void setOrderid(Object orderid) {
        this.orderid = orderid;
    }

    public Integer getRegionid() {
        return regionid;
    }

    public void setRegionid(Integer regionid) {
        this.regionid = regionid;
    }

    public Object getIsRoleid() {
        return isRoleid;
    }

    public void setIsRoleid(Object isRoleid) {
        this.isRoleid = isRoleid;
    }

    public String getGpsLat() {
        return gpsLat;
    }

    public void setGpsLat(String gpsLat) {
        this.gpsLat = gpsLat;
    }

    public String getGpsLong() {
        return gpsLong;
    }

    public void setGpsLong(String gpsLong) {
        this.gpsLong = gpsLong;
    }

    public Object getPlace() {
        return place;
    }

    public void setPlace(Object place) {
        this.place = place;
    }

    public Object getApiKey() {
        return apiKey;
    }

    public void setApiKey(Object apiKey) {
        this.apiKey = apiKey;
    }

    public Object getOrders() {
        return orders;
    }

    public void setOrders(Object orders) {
        this.orders = orders;
    }

    public Object getPaymentOutstanding() {
        return paymentOutstanding;
    }

    public void setPaymentOutstanding(Object paymentOutstanding) {
        this.paymentOutstanding = paymentOutstanding;
    }

    public Object getCforms() {
        return cforms;
    }

    public void setCforms(Object cforms) {
        this.cforms = cforms;
    }

    public Object getChequeStock() {
        return chequeStock;
    }

    public void setChequeStock(Object chequeStock) {
        this.chequeStock = chequeStock;
    }

    public Object getOrdersdata() {
        return ordersdata;
    }

    public void setOrdersdata(Object ordersdata) {
        this.ordersdata = ordersdata;
    }

    public Object getPaymentdata() {
        return paymentdata;
    }

    public void setPaymentdata(Object paymentdata) {
        this.paymentdata = paymentdata;
    }

    public Object getCheckdata() {
        return checkdata;
    }

    public void setCheckdata(Object checkdata) {
        this.checkdata = checkdata;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
}
