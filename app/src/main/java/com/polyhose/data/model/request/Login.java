package com.polyhose.data.model.request;

import java.io.Serializable;

public class Login implements Serializable {

    private long mobile_no;

    private String password;

    public long getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(long mobile_no) {
        this.mobile_no = mobile_no;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
