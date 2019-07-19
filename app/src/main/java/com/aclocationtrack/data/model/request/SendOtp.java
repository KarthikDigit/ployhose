package com.aclocationtrack.data.model.request;

public class SendOtp {

    private long mobile_no;
    private int flag;

    public SendOtp(long mobile_no, int flag) {
        this.mobile_no = mobile_no;
        this.flag = flag;
    }

    public long getMobile_no() {
        return mobile_no;
    }

    public void setMobile_no(long mobile_no) {
        this.mobile_no = mobile_no;
    }

    public long getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }
}
