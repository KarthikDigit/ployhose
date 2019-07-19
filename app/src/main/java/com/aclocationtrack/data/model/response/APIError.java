package com.aclocationtrack.data.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIError implements Parcelable
{

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Object data;
    public final static Parcelable.Creator<APIError> CREATOR = new Creator<APIError>() {


        @SuppressWarnings({
                "unchecked"
        })
        public APIError createFromParcel(Parcel in) {
            return new APIError(in);
        }

        public APIError[] newArray(int size) {
            return (new APIError[size]);
        }

    }
            ;

    protected APIError(Parcel in) {
        this.success = ((Boolean) in.readValue((Boolean.class.getClassLoader())));
        this.message = ((String) in.readValue((String.class.getClassLoader())));
        this.data = ((Data) in.readValue((Data.class.getClassLoader())));
    }

    public APIError() {
    }

    public Boolean getSuccess() {
        return success;
    }

    public void setSuccess(Boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(success);
        dest.writeValue(message);
        dest.writeValue(data);
    }

    public int describeContents() {
        return 0;
    }


    public static class Data implements Parcelable
    {

        @SerializedName("name")
        @Expose
        private List<String> name = null;
        @SerializedName("mobile_no")
        @Expose
        private List<String> mobileNo = null;
        @SerializedName("password")
        @Expose
        private List<String> password = null;
        @SerializedName("c_password")
        @Expose
        private List<String> cPassword = null;
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
            in.readList(this.name, (java.lang.String.class.getClassLoader()));
            in.readList(this.mobileNo, (java.lang.String.class.getClassLoader()));
            in.readList(this.password, (java.lang.String.class.getClassLoader()));
            in.readList(this.cPassword, (java.lang.String.class.getClassLoader()));
        }

        public Data() {
        }

        public List<String> getName() {
            return name;
        }

        public void setName(List<String> name) {
            this.name = name;
        }

        public List<String> getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(List<String> mobileNo) {
            this.mobileNo = mobileNo;
        }

        public List<String> getPassword() {
            return password;
        }

        public void setPassword(List<String> password) {
            this.password = password;
        }

        public List<String> getCPassword() {
            return cPassword;
        }

        public void setCPassword(List<String> cPassword) {
            this.cPassword = cPassword;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeList(name);
            dest.writeList(mobileNo);
            dest.writeList(password);
            dest.writeList(cPassword);
        }

        public int describeContents() {
            return 0;
        }

    }
}