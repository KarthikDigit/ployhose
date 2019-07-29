package com.polyhose.data.model.errorresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LoginDataApiError implements Serializable {

    @SerializedName("mobile_no")
    @Expose
    private List<String> mobileNo = null;
    @SerializedName("password")
    @Expose
    private List<String> password = null;
    private final static long serialVersionUID = 4659322114723599474L;

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

}
