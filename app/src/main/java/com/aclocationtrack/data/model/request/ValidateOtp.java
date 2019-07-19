package com.aclocationtrack.data.model.request;

public class ValidateOtp {

    private long mobile_no;
    private long flag;
    private long otp;

    public ValidateOtp(long mobile_no, long flag, long otp) {
        this.mobile_no = mobile_no;
        this.flag = flag;
        this.otp = otp;
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

    public void setFlag(long flag) {
        this.flag = flag;
    }

    public long getOtp() {
        return otp;
    }

    public void setOtp(long otp) {
        this.otp = otp;
    }
}
