package com.aclocationtrack.data.model.errorresponse;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginMessageApiError extends CommonApiError {

    @SerializedName("data")
    @Expose
    private LoginDataApiError data;

    public LoginDataApiError getData() {
        return data;
    }

    public void setData(LoginDataApiError data) {
        this.data = data;
    }
}
