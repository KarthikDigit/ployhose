package com.polyhose.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProfileDetails {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public static class Data {

        @SerializedName("user")
        @Expose
        private User user;
        @SerializedName("states")
        @Expose
        private List<State> states = null;

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public List<State> getStates() {
            return states;
        }

        public void setStates(List<State> states) {
            this.states = states;
        }


        public static class State {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("state_name")
            @Expose
            private String stateName;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return stateName;
            }

            public String getStateName() {
                return stateName;
            }

            public void setStateName(String stateName) {
                this.stateName = stateName;
            }

        }

        public static class User {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("email")
            @Expose
            private String email;
            @SerializedName("mobile_no")
            @Expose
            private String mobileNo;
            @SerializedName("role_id")
            @Expose
            private String roleId;
            @SerializedName("is_active")
            @Expose
            private String isActive;
            @SerializedName("device_id")
            @Expose
            private String deviceId;
            @SerializedName("push_token_id")
            @Expose
            private String pushTokenId;
            @SerializedName("device_model")
            @Expose
            private String deviceModel;
            @SerializedName("created_by")
            @Expose
            private Object createdBy;
            @SerializedName("address_1")
            @Expose
            private Object address1;
            @SerializedName("address_2")
            @Expose
            private Object address2;
            @SerializedName("city")
            @Expose
            private Object city;
            @SerializedName("state_id")
            @Expose
            private String stateId;
            @SerializedName("zip_code")
            @Expose
            private Object zipCode;
            @SerializedName("created_at")
            @Expose
            private String createdAt;
            @SerializedName("updated_at")
            @Expose
            private String updatedAt;
            @SerializedName("d_mobile")
            @Expose
            private String dMobile;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getMobileNo() {
                return mobileNo;
            }

            public void setMobileNo(String mobileNo) {
                this.mobileNo = mobileNo;
            }

            public String getRoleId() {
                return roleId;
            }

            public void setRoleId(String roleId) {
                this.roleId = roleId;
            }

            public String getIsActive() {
                return isActive;
            }

            public void setIsActive(String isActive) {
                this.isActive = isActive;
            }

            public String getDeviceId() {
                return deviceId;
            }

            public void setDeviceId(String deviceId) {
                this.deviceId = deviceId;
            }

            public String getPushTokenId() {
                return pushTokenId;
            }

            public void setPushTokenId(String pushTokenId) {
                this.pushTokenId = pushTokenId;
            }

            public String getDeviceModel() {
                return deviceModel;
            }

            public void setDeviceModel(String deviceModel) {
                this.deviceModel = deviceModel;
            }

            public Object getCreatedBy() {
                return createdBy;
            }

            public void setCreatedBy(Object createdBy) {
                this.createdBy = createdBy;
            }

            public Object getAddress1() {
                return address1;
            }

            public void setAddress1(Object address1) {
                this.address1 = address1;
            }

            public Object getAddress2() {
                return address2;
            }

            public void setAddress2(Object address2) {
                this.address2 = address2;
            }

            public Object getCity() {
                return city;
            }

            public void setCity(Object city) {
                this.city = city;
            }

            public String getStateId() {
                return stateId;
            }

            public void setStateId(String stateId) {
                this.stateId = stateId;
            }

            public Object getZipCode() {
                return zipCode;
            }

            public void setZipCode(Object zipCode) {
                this.zipCode = zipCode;
            }

            public String getCreatedAt() {
                return createdAt;
            }

            public void setCreatedAt(String createdAt) {
                this.createdAt = createdAt;
            }

            public String getUpdatedAt() {
                return updatedAt;
            }

            public void setUpdatedAt(String updatedAt) {
                this.updatedAt = updatedAt;
            }

            public String getDMobile() {
                return dMobile;
            }

            public void setDMobile(String dMobile) {
                this.dMobile = dMobile;
            }

        }


    }

}
