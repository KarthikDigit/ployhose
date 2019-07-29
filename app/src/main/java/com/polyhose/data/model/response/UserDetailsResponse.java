package com.polyhose.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UserDetailsResponse implements Parcelable
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
    public final static Parcelable.Creator<UserDetailsResponse> CREATOR = new Creator<UserDetailsResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public UserDetailsResponse createFromParcel(Parcel in) {
            return new UserDetailsResponse(in);
        }

        public UserDetailsResponse[] newArray(int size) {
            return (new UserDetailsResponse[size]);
        }

    }
            ;

    protected UserDetailsResponse(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
    }

    public UserDetailsResponse() {
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
        private Integer roleId;
        @SerializedName("is_active")
        @Expose
        private Integer isActive;
        @SerializedName("created_by")
        @Expose
        private Object createdBy;
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
            this.name = ((String) in.readValue((String.class.getClassLoader())));
            this.email = ((String) in.readValue((String.class.getClassLoader())));
            this.mobileNo = ((String) in.readValue((String.class.getClassLoader())));
            this.roleId = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.isActive = ((Integer) in.readValue((Integer.class.getClassLoader())));
            this.createdBy = ((Object) in.readValue((Object.class.getClassLoader())));
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

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }

        public Integer getIsActive() {
            return isActive;
        }

        public void setIsActive(Integer isActive) {
            this.isActive = isActive;
        }

        public Object getCreatedBy() {
            return createdBy;
        }

        public void setCreatedBy(Object createdBy) {
            this.createdBy = createdBy;
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
            dest.writeValue(name);
            dest.writeValue(email);
            dest.writeValue(mobileNo);
            dest.writeValue(roleId);
            dest.writeValue(isActive);
            dest.writeValue(createdBy);
            dest.writeValue(createdAt);
            dest.writeValue(updatedAt);
        }

        public int describeContents() {
            return 0;
        }

    }

}