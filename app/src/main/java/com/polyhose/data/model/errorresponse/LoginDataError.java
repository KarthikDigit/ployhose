package com.polyhose.data.model.errorresponse;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class LoginDataError implements Serializable {


    @SerializedName("email")
    @Expose
    private List<String> email = null;
    @SerializedName("password")
    @Expose
    private List<String> password = null;

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }

    public List<String> getPassword() {
        return password;
    }

    public void setPassword(List<String> password) {
        this.password = password;
    }


}
