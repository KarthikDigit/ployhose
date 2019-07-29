package com.polyhose.data.model.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MobileErrorApi implements Serializable {

    @SerializedName("success")
    @Expose
    private Boolean success;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;
    private final static long serialVersionUID = -6455777666134805155L;

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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    public class Data implements Serializable {

        @SerializedName("mobile_no")
        @Expose
        private List<String> mobileNo = null;
        private final static long serialVersionUID = -3883951070421225431L;

        public List<String> getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(List<String> mobileNo) {
            this.mobileNo = mobileNo;
        }

    }
}