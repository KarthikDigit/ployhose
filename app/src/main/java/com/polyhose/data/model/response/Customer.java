package com.polyhose.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Customer implements Serializable {


    @SerializedName("customer_ID")
    @Expose
    private Integer customerID;
    @SerializedName("customer_name")
    @Expose
    private String customerName = "";
    @SerializedName("customer_Code")
    @Expose
    private Object customerCode;
    @SerializedName("customer_address")
    @Expose
    private String customerAddress;
    @SerializedName("customer_city")
    @Expose
    private String customerCity;
    @SerializedName("customer_state")
    @Expose
    private String customerState;
    @SerializedName("customer_TelePhone1")
    @Expose
    private Object customerTelePhone1;
    @SerializedName("customer_address1")
    @Expose
    private Object customerAddress1;
    @SerializedName("customer_city1")
    @Expose
    private String customerCity1;
    @SerializedName("customer_state1")
    @Expose
    private Object customerState1;
    @SerializedName("customer_TelePhone2")
    @Expose
    private Object customerTelePhone2;
    @SerializedName("customer_type")
    @Expose
    private Integer customerType;
    @SerializedName("customer_Remark")
    @Expose
    private String customerRemark;
    @SerializedName("createdby")
    @Expose
    private String createdby;
    @SerializedName("leadby")
    @Expose
    private String leadby;
    @SerializedName("customer_phone")
    @Expose
    private String customerPhone;
    @SerializedName("customer_email")
    @Expose
    private String customerEmail;
    @SerializedName("lastmodifyby")
    @Expose
    private String lastmodifyby;
    @SerializedName("customer_lastmodifytime")
    @Expose
    private String customerLastmodifytime;
    @SerializedName("customer_createtime")
    @Expose
    private String customerCreatetime;
    @SerializedName("customer_contactperson")
    @Expose
    private String customerContactperson;
    @SerializedName("customer_zipcode")
    @Expose
    private String customerZipcode;
    @SerializedName("customer_zipcode1")
    @Expose
    private Object customerZipcode1;
    @SerializedName("customer_region_id")
    @Expose
    private Integer customerRegionId;
    @SerializedName("customer_status")
    @Expose
    private Integer customerStatus;
    @SerializedName("industrialID")
    @Expose
    private Integer industrialID;
    @SerializedName("pan_No")
    @Expose
    private String panNo;
    @SerializedName("csT_No")
    @Expose
    private String csTNo;
    @SerializedName("tiN_No")
    @Expose
    private String tiNNo;
    @SerializedName("size_Of_Business")
    @Expose
    private Object sizeOfBusiness;
    @SerializedName("no_Of_Employers")
    @Expose
    private Object noOfEmployers;
    @SerializedName("potential")
    @Expose
    private Integer potential;
    @SerializedName("handled_By")
    @Expose
    private String handledBy;
    @SerializedName("companyType")
    @Expose
    private Integer companyType;
    @SerializedName("promoterDetails")
    @Expose
    private Object promoterDetails;
    @SerializedName("businessDetails")
    @Expose
    private String businessDetails;
    @SerializedName("competitorPurchasing")
    @Expose
    private Object competitorPurchasing;
    @SerializedName("currentProduciton")
    @Expose
    private Object currentProduciton;
    @SerializedName("expectedAny")
    @Expose
    private Object expectedAny;
    @SerializedName("whatbuying")
    @Expose
    private Object whatbuying;
    @SerializedName("quantumBusiness")
    @Expose
    private Object quantumBusiness;
    @SerializedName("whyNotPreferring")
    @Expose
    private Object whyNotPreferring;
    @SerializedName("handledById")
    @Expose
    private Integer handledById;
    private final static long serialVersionUID = 633471501035462612L;

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

    public Object getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(Object customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public Object getCustomerTelePhone1() {
        return customerTelePhone1;
    }

    public void setCustomerTelePhone1(Object customerTelePhone1) {
        this.customerTelePhone1 = customerTelePhone1;
    }

    public Object getCustomerAddress1() {
        return customerAddress1;
    }

    public void setCustomerAddress1(Object customerAddress1) {
        this.customerAddress1 = customerAddress1;
    }

    public String getCustomerCity1() {
        return customerCity1;
    }

    public void setCustomerCity1(String customerCity1) {
        this.customerCity1 = customerCity1;
    }

    public Object getCustomerState1() {
        return customerState1;
    }

    public void setCustomerState1(Object customerState1) {
        this.customerState1 = customerState1;
    }

    public Object getCustomerTelePhone2() {
        return customerTelePhone2;
    }

    public void setCustomerTelePhone2(Object customerTelePhone2) {
        this.customerTelePhone2 = customerTelePhone2;
    }

    public Integer getCustomerType() {
        return customerType;
    }

    public void setCustomerType(Integer customerType) {
        this.customerType = customerType;
    }

    public String getCustomerRemark() {
        return customerRemark;
    }

    public void setCustomerRemark(String customerRemark) {
        this.customerRemark = customerRemark;
    }

    public String getCreatedby() {
        return createdby;
    }

    public void setCreatedby(String createdby) {
        this.createdby = createdby;
    }

    public String getLeadby() {
        return leadby;
    }

    public void setLeadby(String leadby) {
        this.leadby = leadby;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getLastmodifyby() {
        return lastmodifyby;
    }

    public void setLastmodifyby(String lastmodifyby) {
        this.lastmodifyby = lastmodifyby;
    }

    public String getCustomerLastmodifytime() {
        return customerLastmodifytime;
    }

    public void setCustomerLastmodifytime(String customerLastmodifytime) {
        this.customerLastmodifytime = customerLastmodifytime;
    }

    public String getCustomerCreatetime() {
        return customerCreatetime;
    }

    public void setCustomerCreatetime(String customerCreatetime) {
        this.customerCreatetime = customerCreatetime;
    }

    public String getCustomerContactperson() {
        return customerContactperson;
    }

    public void setCustomerContactperson(String customerContactperson) {
        this.customerContactperson = customerContactperson;
    }

    public String getCustomerZipcode() {
        return customerZipcode;
    }

    public void setCustomerZipcode(String customerZipcode) {
        this.customerZipcode = customerZipcode;
    }

    public Object getCustomerZipcode1() {
        return customerZipcode1;
    }

    public void setCustomerZipcode1(Object customerZipcode1) {
        this.customerZipcode1 = customerZipcode1;
    }

    public Integer getCustomerRegionId() {
        return customerRegionId;
    }

    public void setCustomerRegionId(Integer customerRegionId) {
        this.customerRegionId = customerRegionId;
    }

    public Integer getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(Integer customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Integer getIndustrialID() {
        return industrialID;
    }

    public void setIndustrialID(Integer industrialID) {
        this.industrialID = industrialID;
    }

    public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getCsTNo() {
        return csTNo;
    }

    public void setCsTNo(String csTNo) {
        this.csTNo = csTNo;
    }

    public String getTiNNo() {
        return tiNNo;
    }

    public void setTiNNo(String tiNNo) {
        this.tiNNo = tiNNo;
    }

    public Object getSizeOfBusiness() {
        return sizeOfBusiness;
    }

    public void setSizeOfBusiness(Object sizeOfBusiness) {
        this.sizeOfBusiness = sizeOfBusiness;
    }

    public Object getNoOfEmployers() {
        return noOfEmployers;
    }

    public void setNoOfEmployers(Object noOfEmployers) {
        this.noOfEmployers = noOfEmployers;
    }

    public Integer getPotential() {
        return potential;
    }

    public void setPotential(Integer potential) {
        this.potential = potential;
    }

    public String getHandledBy() {
        return handledBy;
    }

    public void setHandledBy(String handledBy) {
        this.handledBy = handledBy;
    }

    public Integer getCompanyType() {
        return companyType;
    }

    public void setCompanyType(Integer companyType) {
        this.companyType = companyType;
    }

    public Object getPromoterDetails() {
        return promoterDetails;
    }

    public void setPromoterDetails(Object promoterDetails) {
        this.promoterDetails = promoterDetails;
    }

    public String getBusinessDetails() {
        return businessDetails;
    }

    public void setBusinessDetails(String businessDetails) {
        this.businessDetails = businessDetails;
    }

    public Object getCompetitorPurchasing() {
        return competitorPurchasing;
    }

    public void setCompetitorPurchasing(Object competitorPurchasing) {
        this.competitorPurchasing = competitorPurchasing;
    }

    public Object getCurrentProduciton() {
        return currentProduciton;
    }

    public void setCurrentProduciton(Object currentProduciton) {
        this.currentProduciton = currentProduciton;
    }

    public Object getExpectedAny() {
        return expectedAny;
    }

    public void setExpectedAny(Object expectedAny) {
        this.expectedAny = expectedAny;
    }

    public Object getWhatbuying() {
        return whatbuying;
    }

    public void setWhatbuying(Object whatbuying) {
        this.whatbuying = whatbuying;
    }

    public Object getQuantumBusiness() {
        return quantumBusiness;
    }

    public void setQuantumBusiness(Object quantumBusiness) {
        this.quantumBusiness = quantumBusiness;
    }

    public Object getWhyNotPreferring() {
        return whyNotPreferring;
    }

    public void setWhyNotPreferring(Object whyNotPreferring) {
        this.whyNotPreferring = whyNotPreferring;
    }

    public Integer getHandledById() {
        return handledById;
    }

    public void setHandledById(Integer handledById) {
        this.handledById = handledById;
    }


}
