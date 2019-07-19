package com.aclocationtrack.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PostedServicesRes implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("data")
    @Expose
    private Data data;
    @SerializedName("message")
    @Expose
    private String message;
    public final static Parcelable.Creator<PostedServicesRes> CREATOR = new Creator<PostedServicesRes>() {


        @SuppressWarnings({
                "unchecked"
        })
        public PostedServicesRes createFromParcel(Parcel in) {
            return new PostedServicesRes(in);
        }

        public PostedServicesRes[] newArray(int size) {
            return (new PostedServicesRes[size]);
        }

    }
            ;

    protected PostedServicesRes(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public PostedServicesRes() {
    }

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

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(data);
        dest.writeValue(message);
    }

    public int describeContents() {
        return 0;
    }



    public static class Data implements Parcelable
    {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("user_id")
        @Expose
        private String userId;
        @SerializedName("assigned_by")
        @Expose
        private Object assignedBy;
        @SerializedName("assigned_to")
        @Expose
        private Object assignedTo;
        @SerializedName("customer_email")
        @Expose
        private String customerEmail;
        @SerializedName("address_1")
        @Expose
        private String address1;
        @SerializedName("address_2")
        @Expose
        private String address2;
        @SerializedName("city")
        @Expose
        private String city;
        @SerializedName("state_id")
        @Expose
        private String stateId;
        @SerializedName("zipcode")
        @Expose
        private String zipcode;
        @SerializedName("mobile_no")
        @Expose
        private String mobileNo;
        @SerializedName("brand_id")
        @Expose
        private String brandId;
        @SerializedName("model_name")
        @Expose
        private String modelName;
        @SerializedName("ton_capacity")
        @Expose
        private String tonCapacity;
        @SerializedName("problem_type_id")
        @Expose
        private String problemTypeId;
        @SerializedName("place_at_home")
        @Expose
        private String placeAtHome;
        @SerializedName("complaint")
        @Expose
        private String complaint;
        @SerializedName("prefer_time_id")
        @Expose
        private String preferTimeId;
        @SerializedName("prefer_date")
        @Expose
        private String preferDate;
        @SerializedName("comments")
        @Expose
        private Object comments;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        public final static Parcelable.Creator<Data> CREATOR = new Creator<Data>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Data createFromParcel(Parcel in) {
                return new Data(in);
            }

            public Data[] newArray(int size) {
                return (new Data[size]);
            }

        }
                ;

        protected Data(Parcel in) {
            this.id = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.userId = ((String) in.readValue((String.class.getClassLoader())));
            this.assignedBy = ((Object) in.readValue((Object.class.getClassLoader())));
            this.assignedTo = ((Object) in.readValue((Object.class.getClassLoader())));
            this.customerEmail = ((String) in.readValue((String.class.getClassLoader())));
            this.address1 = ((String) in.readValue((String.class.getClassLoader())));
            this.address2 = ((String) in.readValue((String.class.getClassLoader())));
            this.city = ((String) in.readValue((String.class.getClassLoader())));
            this.stateId = ((String) in.readValue((String.class.getClassLoader())));
            this.zipcode = ((String) in.readValue((String.class.getClassLoader())));
            this.mobileNo = ((String) in.readValue((String.class.getClassLoader())));
            this.brandId = ((String) in.readValue((String.class.getClassLoader())));
            this.modelName = ((String) in.readValue((String.class.getClassLoader())));
            this.tonCapacity = ((String) in.readValue((String.class.getClassLoader())));
            this.problemTypeId = ((String) in.readValue((String.class.getClassLoader())));
            this.placeAtHome = ((String) in.readValue((String.class.getClassLoader())));
            this.complaint = ((String) in.readValue((String.class.getClassLoader())));
            this.preferTimeId = ((String) in.readValue((String.class.getClassLoader())));
            this.preferDate = ((String) in.readValue((String.class.getClassLoader())));
            this.comments = ((Object) in.readValue((Object.class.getClassLoader())));
            this.status = ((String) in.readValue((String.class.getClassLoader())));
            this.createdAt = ((String) in.readValue((String.class.getClassLoader())));
            this.updatedAt = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Data() {
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public Object getAssignedBy() {
            return assignedBy;
        }

        public void setAssignedBy(Object assignedBy) {
            this.assignedBy = assignedBy;
        }

        public Object getAssignedTo() {
            return assignedTo;
        }

        public void setAssignedTo(Object assignedTo) {
            this.assignedTo = assignedTo;
        }

        public String getCustomerEmail() {
            return customerEmail;
        }

        public void setCustomerEmail(String customerEmail) {
            this.customerEmail = customerEmail;
        }

        public String getAddress1() {
            return address1;
        }

        public void setAddress1(String address1) {
            this.address1 = address1;
        }

        public String getAddress2() {
            return address2;
        }

        public void setAddress2(String address2) {
            this.address2 = address2;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getStateId() {
            return stateId;
        }

        public void setStateId(String stateId) {
            this.stateId = stateId;
        }

        public String getZipcode() {
            return zipcode;
        }

        public void setZipcode(String zipcode) {
            this.zipcode = zipcode;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getBrandId() {
            return brandId;
        }

        public void setBrandId(String brandId) {
            this.brandId = brandId;
        }

        public String getModelName() {
            return modelName;
        }

        public void setModelName(String modelName) {
            this.modelName = modelName;
        }

        public String getTonCapacity() {
            return tonCapacity;
        }

        public void setTonCapacity(String tonCapacity) {
            this.tonCapacity = tonCapacity;
        }

        public String getProblemTypeId() {
            return problemTypeId;
        }

        public void setProblemTypeId(String problemTypeId) {
            this.problemTypeId = problemTypeId;
        }

        public String getPlaceAtHome() {
            return placeAtHome;
        }

        public void setPlaceAtHome(String placeAtHome) {
            this.placeAtHome = placeAtHome;
        }

        public String getComplaint() {
            return complaint;
        }

        public void setComplaint(String complaint) {
            this.complaint = complaint;
        }

        public String getPreferTimeId() {
            return preferTimeId;
        }

        public void setPreferTimeId(String preferTimeId) {
            this.preferTimeId = preferTimeId;
        }

        public String getPreferDate() {
            return preferDate;
        }

        public void setPreferDate(String preferDate) {
            this.preferDate = preferDate;
        }

        public Object getComments() {
            return comments;
        }

        public void setComments(Object comments) {
            this.comments = comments;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
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

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(userId);
            dest.writeValue(assignedBy);
            dest.writeValue(assignedTo);
            dest.writeValue(customerEmail);
            dest.writeValue(address1);
            dest.writeValue(address2);
            dest.writeValue(city);
            dest.writeValue(stateId);
            dest.writeValue(zipcode);
            dest.writeValue(mobileNo);
            dest.writeValue(brandId);
            dest.writeValue(modelName);
            dest.writeValue(tonCapacity);
            dest.writeValue(problemTypeId);
            dest.writeValue(placeAtHome);
            dest.writeValue(complaint);
            dest.writeValue(preferTimeId);
            dest.writeValue(preferDate);
            dest.writeValue(comments);
            dest.writeValue(status);
            dest.writeValue(createdAt);
            dest.writeValue(updatedAt);
        }

        public int describeContents() {
            return 0;
        }

    }


}