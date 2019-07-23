package com.aclocationtrack.auth.forgotpassword;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.aclocationtrack.R;
import com.aclocationtrack.base.BaseActivity;
import com.aclocationtrack.auth.changepassword.ChangePasswordActivity;
import com.aclocationtrack.common.AppSignatureHelper;
import com.aclocationtrack.common.SMSRetriver;
import com.aclocationtrack.common.SMSRetriver.CallBack;
import com.aclocationtrack.utility.KeyboardUtils;
import com.aclocationtrack.utility.TextInputUtil;
import com.aclocationtrack.utility.ValidationUtil;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.aclocationtrack.utility.ProgressUtils.hideProgress;
import static com.aclocationtrack.utility.ProgressUtils.showProgress;

public class ForgotPasswordActivity extends BaseActivity implements CallBack {

    private static final String TAG = "ForgotPasswordActivity";

    @BindView(R.id.mobile_no)
    TextInputLayout mMobileNumber;
    @BindView(R.id.otp_input)
    TextInputLayout otpInput;
    @BindView(R.id.btn_otp)
    Button btnOtp;
    @BindView(R.id.otpView)
    CardView otpView;
    @BindView(R.id.loginView)
    CardView loginView;

    @BindView(R.id.please)
    TextView please;


    private SMSRetriver mSmsRetriver;
    private AppSignatureHelper appSignatureHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ButterKnife.bind(this);
        setButterKnife();
        appSignatureHelper = new AppSignatureHelper(this);
        mSmsRetriver = new SMSRetriver(this, this);
        smsReceiver();
    }

    private void smsReceiver() {


        //set phone number filter if needed
//        smsVerifyCatcher.setPhoneNumberFilter("777");
//smsVerifyCatcher.setFilter("regexp");
    }


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @OnClick({R.id.back, R.id.forgot_btn_send, R.id.forgot_login_now, R.id.btn_otp})
    public void onCLick(View v) {

        switch (v.getId()) {

            case R.id.back:


                if (isOTPView) {
                    changeOTPView(false);
                    otpInput.getEditText().setText("");
                } else {
                    onBackPressed();
                }

                break;

            case R.id.btn_otp:

                validateOtp();

//                String code = TextInputUtil.getText(otpInput);
//                if (!code.isEmpty()) {
//                    otpInput.getEditText().setText("");
//                    if (mSmsValidate != null) {
//                        mSmsValidate.verify(code);
//                    }
//                }

                break;

            case R.id.forgot_btn_send:

                if (validateFormInputFieldsAll()) {
                    KeyboardUtils.hideKeyboard(this);
                    methodRequiresTwoPermission();

                } else {
                    showToast(getString(R.string.allfieldsrequired));
                }

                break;

            case R.id.forgot_login_now:
                onBackPressed();
                break;

        }

    }


    private boolean validateFormInputFieldsAll() {

        boolean isValid = true;

        if (TextUtils.isEmpty(mMobileNumber.getEditText().getText())) {
            TextInputUtil.setError(mMobileNumber, getString(R.string.mobileempty));
            isValid = false;
        } else if (ValidationUtil.isValidMobile(mMobileNumber.getEditText().getText())) {
            TextInputUtil.setError(mMobileNumber, getString(R.string.mobile_must_be));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(mMobileNumber);
        }

        return isValid;

    }


    private void methodRequiresTwoPermission() {

        validateAccount();

    }


    private void validateAccount() {

        Map<String, String> params = new HashMap<>();
        params.put("mobile_no", mMobileNumber.getEditText().getText().toString());


        showLoading();

//        dataSource.mobileValidate(new JSONObject(params).toString(), new DataListener() {
//            @Override
//            public void onSuccess(Object object) {
//
//                hideLoading();
//                MobileValidate validate = (MobileValidate) object;
//
//                if (!validate.getSuccess()) {
//
////                    String mPhoneNumber = "+91" + TextInputUtil.getText(mMobileNumber);
////
////                    mSmsValidate.sendSMS(mPhoneNumber);
//
//                    generateOtp();
//
//
//                } else {
//
//                    showToast("This mobile number is not in our system,Please enter a valid mobile number");
//
//                }
//            }
//
//            @Override
//            public void onFail(Throwable throwable) {
//                hideLoading();
//
//                try {
//                    HttpException error = (HttpException) throwable;
//                    String errorBody = error.response().errorBody().string();
//
//
//                    MobileErrorApi errorApi = new Gson().fromJson(errorBody, MobileErrorApi.class);
//
//                    Log.e(TAG, "onFail: " + new Gson().toJson(errorApi));
//
//                    if (!errorApi.getSuccess()) {
//                        generateOtp();
//                    } else {
//
//                        showToast(errorApi.getMessage());
//                    }
//
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//
//
////                showToast("This mobile number is not in our system,Please enter correct number");
//
//            }
//
//            @Override
//            public void onNetworkFailure() {
//
//                hideLoading();
//                showToast("No internet");
//            }
//        });
    }


    private void generateOtp() {


        new Thread(new Runnable() {
            @Override
            public void run() {
                mSmsRetriver.startSmsRetriever();
            }
        }).start();


        long mo = Long.parseLong(mMobileNumber.getEditText().getText().toString());


        Map<String, Object> params = new HashMap<>();
        params.put("mobile_no", mo);
        params.put("flag", 2);
        params.put("secret", appSignatureHelper.getAppSignatures().get(0));

        Log.e(TAG, "generateOtp: " + new JSONObject(params).toString());

        showLoading();

//        dataSource.generateOtp(new JSONObject(params).toString(), new DataListener() {
//            @Override
//            public void onSuccess(Object object) {
//
//                hideLoading();
//                OTPSendApi otpSendApi = (OTPSendApi) object;
//
//                if (otpSendApi.getSuccess()) {
//
//                    changeOTPView(true);
//
//                } else {
//
//                    showToast(otpSendApi.getMessage());
//                }
//            }
//
//            @Override
//            public void onFail(Throwable throwable) {
//                hideLoading();
//                Log.e(TAG, "onFail: " + throwable.getMessage());
//                showToast("Invalid Mobile number");
//            }
//
//            @Override
//            public void onNetworkFailure() {
//                hideLoading();
//                showToast("No internet");
//            }
//        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSmsRetriver != null) {
            mSmsRetriver.unRegisterBroadcast();
            mSmsRetriver = null;
        }
    }

    private void validateOtp() {
        long code = Long.parseLong(otpInput.getEditText().getText().toString());
        long mo = Long.parseLong(mMobileNumber.getEditText().getText().toString());

        Map<String, Object> params = new HashMap<>();
        params.put("mobile_no", mo);
        params.put("flag", 2);
        params.put("otp", code);

        Log.e(TAG, "validateOtp: " + new JSONObject(params).toString());

        showLoading();
//        dataSource.validateOtp(new JSONObject(params).toString(), new DataListener() {
//            @Override
//            public void onSuccess(Object object) {
//                hideLoading();
//
//                OTPValidateApi otpValidateApi = (OTPValidateApi) object;
//
//                if (otpValidateApi.getSuccess()) {
//                    showCompleted();
//
//                } else {
//
//                    showToast(otpValidateApi.getMessage());
//                }
//
//            }
//
//            @Override
//            public void onFail(Throwable throwable) {
//                hideLoading();
//                Log.e(TAG, "onFail: " + throwable.getMessage());
//                showToast("Given OTP is wrong..!");
//            }
//
//            @Override
//            public void onNetworkFailure() {
//                hideLoading();
//                showToast("No internet");
//            }
//        });

    }

    public void changeOTPView(boolean isChange) {
        enableInputField(isChange);
    }

    private boolean isOTPView = false;

    private void enableInputField(boolean b) {

        if (b) {

            isOTPView = true;
            loginView.setVisibility(View.INVISIBLE);
            otpView.setVisibility(View.VISIBLE);
            please.setVisibility(View.VISIBLE);

        } else {
            isOTPView = false;
            loginView.setVisibility(View.VISIBLE);
            otpView.setVisibility(View.INVISIBLE);
        }

    }

    private void showCompleted() {

        hideProgress();
        Toast.makeText(this, "Mobile number is verified", Toast.LENGTH_SHORT).show();


        Intent intent = new Intent(this, ChangePasswordActivity.class);

        intent.putExtra("mobile_no", mMobileNumber.getEditText().getText().toString());

        startActivityForResult(intent, 123);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 123) {


            finish();


        }
    }

    @Override
    public void showOTPCode(String code) {

        otpInput.getEditText().setText(code);


        please.setVisibility(View.GONE);

    }

    @Override
    public void onFailure(Exception e) {
        super.onFailure(e);

        showToast(e.getMessage());
    }
}
