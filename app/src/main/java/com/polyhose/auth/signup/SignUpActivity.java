package com.polyhose.auth.signup;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.polyhose.R;
import com.polyhose.base.BaseActivity;
import com.polyhose.common.AppSignatureHelper;
import com.polyhose.common.SMSRetriver;
import com.polyhose.common.SMSRetriver.CallBack;
import com.polyhose.data.helper.NetworkHelper;
import com.polyhose.data.model.response.APIError;
import com.polyhose.data.model.response.RegisterResponse;
import com.polyhose.auth.login.LoginActivity;
import com.polyhose.utility.KeyboardUtils;
import com.polyhose.utility.TextInputUtil;
import com.polyhose.utility.ValidationUtil;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.polyhose.utility.ProgressUtils.hideProgress;
import static com.polyhose.utility.ProgressUtils.showProgress;

public class SignUpActivity extends BaseActivity implements SignUpPresenter.ResponseCallBack, CallBack {

    private static final String TAG = "SignUpActivity";
    @BindView(R.id.back)
    ImageView mBack;
    @BindView(R.id.terms)
    TextView mTerms;
    @BindView(R.id.sign_input_txt_name)
    TextInputLayout mSignUpName;
    @BindView(R.id.sign_input_txt_mobile)
    TextInputLayout mSignUpMobile;
    @BindView(R.id.sign_input_txt_email)
    TextInputLayout mSignUpEmail;
    @BindView(R.id.sign_input_txt_password)
    TextInputLayout mSignUpPassword;
    @BindView(R.id.sign_input_txt_con_password)
    TextInputLayout mSignUpConfirmPassword;
    @BindView(R.id.sign_input_btn_signup)
    Button mSignUp;
    @BindView(R.id.sign_txt_login_now)
    TextView mSignUpLoginNow;
    @BindView(R.id.otp_input)
    TextInputLayout otpInput;
    @BindView(R.id.btn_otp)
    Button btnOtp;
    @BindView(R.id.otpView)
    CardView otpView;
    @BindView(R.id.signupView)
    CardView signupView;

    @BindView(R.id.please)
    TextView please;

    private SMSRetriver mSmsRetriver;
    private AppSignatureHelper appSignatureHelper;

    private SignUpPresenter signUpPresenter;
    private static final int RC_SMS_SEND_RECEVE = 1234;
    private static final String[] SMS_PERMISSIONS = {Manifest.permission.INTERNET,
            Manifest.permission.READ_SMS,
            Manifest.permission.RECEIVE_SMS,
            Manifest.permission.ACCESS_NETWORK_STATE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        setButterKnife();
        appSignatureHelper = new AppSignatureHelper(this);
        mSmsRetriver = new SMSRetriver(this, this);
        mTerms.setText(Html.fromHtml(getString(R.string.terms)));
        mTerms.setMovementMethod(LinkMovementMethod.getInstance());

//        mSignUpName.setFocusable(true);
//        mSignUpName.setFocusableInTouchMode(true);
//        mSignUpName.getEditText().setFocusable(true);
        mSignUpName.getEditText().setFocusableInTouchMode(true);


//        TextInputUtil.setErrorFocusable(mSignUpMobile, "This number will be verified via OTP ",R.style.before_error_appearance);

        KeyboardUtils.hideKeyboard(this);

        signUpPresenter = new SignUpPresenter(dataSource, this);


        smsReceiver();
    }


    private void smsReceiver() {
        //init SmsVerifyCatcher


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


    @OnClick({R.id.sign_txt_login_now, R.id.sign_input_btn_signup, R.id.back, R.id.btn_otp})
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btn_otp:


                String code = otpInput.getEditText().getText().toString();

                showToast(code);

                validateOtp();

//                String code = TextInputUtil.getText(otpInput);
//                if (!code.isEmpty()) {
//                    otpInput.getEditText().setText("");
//                    if (mSmsValidate != null) {
//                        mSmsValidate.verify(code);
//                    }
//                }

                break;

            case R.id.back:

                if (isOtpView) {

                    changeOTPView(false);
                    otpInput.getEditText().setText("");
                } else {

                    SignUpActivity.super.onBackPressed();
                }
                break;

            case R.id.sign_txt_login_now:
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                break;

            case R.id.sign_input_btn_signup:


                if (NetworkHelper.isNetworkAvailable(this)) {
                    if (validateFormInputFieldsAll()) {
                        KeyboardUtils.hideKeyboard(this);
//                showCompleted();
                        methodRequiresTwoPermission();


//                    showToast("Valid");


//                    Map<String,Object> register=new HashMap<>();
//
//                    register.put("name",TextInputUtil.getText(mSignUpName));
//                    register.put("mobile_no",Long.parseLong(TextInputUtil.getText(mSignUpMobile)));
//                    register.put("password",TextInputUtil.getText(mSignUpPassword));
//                    register.put("c_password",TextInputUtil.getText(mSignUpPassword));
//
//                    signUpPresenter.callRegister(new JSONObject(register).toString(),this);
//
                    } else {

                        showToast(getString(R.string.allfieldsrequired));

                    }
                } else showToast("There is no internet, Please try again");

                break;


        }

    }


    private void methodRequiresTwoPermission() {

        validateAccount();

//            showCompleted();


    }


    private void validateAccount() {

        Map<String, String> params = new HashMap<>();
        params.put("mobile_no", mSignUpMobile.getEditText().getText().toString());


        showLoading();

//        dataSource.mobileValidate(new JSONObject(params).toString(), new DataListener() {
//            @Override
//            public void onSuccess(Object object) {
//
//                hideLoading();
//                MobileValidate validate = (MobileValidate) object;
//
//                if (validate.getSuccess()) {
//
////                    showToast("success");
//
//                    generateOtp();
//
////                    String mPhoneNumber = "+91" + TextInputUtil.getText(mSignUpMobile);
////
////                    mSmsValidate.sendSMS(mPhoneNumber);
//
//                } else {
//
//                    showToast("This mobile number already taken, please enter another one");
//
//                }
//            }
//
//            @Override
//            public void onFail(Throwable throwable) {
//
//                hideLoading();
//
////                Converter<ResponseBody, APIError> converter =
////                        RetrofitClient.getRetrofit()
////                                .responseBodyConverter(APIError.class, new Annotation[0]);
////
////                APIError apiError = null;
////                try {
////                    apiError = converter.convert(((HttpException) throwable).response().errorBody());
////                } catch (IOException e) {
//////            Logger.e(e.getMessage());
////
////                    Log.e(TAG, "parseError: " + e.getMessage());
////
////                }
////
////
////                try {
////                    Log.e(TAG, "onFail: error body " + ((HttpException) throwable).response().errorBody().string());
////
////
//////                    APIError apiError = gson.fromJson(((HttpException) throwable).response().errorBody().string(), APIError.class);
////
////                    if (apiError != null) {
////                        if (apiError.getData() instanceof DataString) {
////
////                            Log.e(TAG, "onFail: string " + apiError.getData().toString());
////
////                        } else {
////
////
////
////                            Log.e(TAG, "onFail: data " + ((APIError.Data)apiError.getData()).getMobileNo().get(0));
////
////                        }
////                    }
////
////                } catch (IOException | NullPointerException e) {
////                    Log.e(TAG, "onFail: ee " + e.getMessage());
////                }
//
//
//                showToast("This mobile number has already taken, please enter another one ");
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

        long mo = Long.parseLong(mSignUpMobile.getEditText().getText().toString());


        Map<String, Object> params = new HashMap<>();
        params.put("mobile_no", mo);
        params.put("flag", 1);
        params.put("secret", appSignatureHelper.getAppSignatures().get(0));


        Log.e(TAG, "generateOtp: " + new JSONObject(params).toString());


        mSmsRetriver.startSmsRetriever();
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
//                showToast("Something went wrong");
//            }
//
//            @Override
//            public void onNetworkFailure() {
//                hideLoading();
//                showToast("No internet");
//            }
//        });


    }

    private void validateOtp() {
        long code = Long.parseLong(otpInput.getEditText().getText().toString());
        long mo = Long.parseLong(mSignUpMobile.getEditText().getText().toString());

        Map<String, Object> params = new HashMap<>();
        params.put("mobile_no", mo);
        params.put("flag", 1);
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

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mSmsRetriver != null) {

            mSmsRetriver.unRegisterBroadcast();
            mSmsRetriver = null;

        }
    }

    public void changeOTPView(boolean isChange) {

        enableInputField(isChange);

    }

    private boolean isOtpView = false;

    private void enableInputField(boolean b) {

        if (b) {

            isOtpView = true;
            signupView.setVisibility(View.GONE);
            otpView.setVisibility(View.VISIBLE);
            please.setVisibility(View.VISIBLE);

        } else {
            isOtpView = false;
            signupView.setVisibility(View.VISIBLE);
            otpView.setVisibility(View.GONE);
        }

    }


    private void showCompleted() {

        hideProgress();
        Toast.makeText(this, "Mobile number is verified", Toast.LENGTH_SHORT).show();

        Map<String, Object> register = new HashMap<>();

        register.put("name", TextInputUtil.getText(mSignUpName));
        register.put("email", TextInputUtil.getText(mSignUpEmail));
        register.put("mobile_no", Long.parseLong(TextInputUtil.getText(mSignUpMobile)));
        register.put("password", TextInputUtil.getText(mSignUpPassword));
        register.put("c_password", TextInputUtil.getText(mSignUpPassword));


        signUpPresenter.callRegister(new JSONObject(register).toString(), this);


    }


    private boolean validateFormInputFieldsAll() {

        boolean isValid = true;

        if (TextUtils.isEmpty(mSignUpName.getEditText().getText())) {
            TextInputUtil.setError(mSignUpName, getString(R.string.fullnameempty));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(mSignUpName);
        }


        if (TextUtils.isEmpty(mSignUpMobile.getEditText().getText())) {
            TextInputUtil.setError(mSignUpMobile, getString(R.string.mobileempty));
            isValid = false;
        } else if (ValidationUtil.isValidMobile(mSignUpMobile.getEditText().getText())) {
            TextInputUtil.setError(mSignUpMobile, getString(R.string.mobile_must_be));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(mSignUpMobile);
        }


        if (TextUtils.isEmpty(mSignUpEmail.getEditText().getText())) {
            TextInputUtil.setError(mSignUpEmail, getString(R.string.emailempty));
            isValid = false;
        } else if (!ValidationUtil.isEmail(mSignUpEmail.getEditText().getText())) {
            TextInputUtil.setError(mSignUpEmail, getString(R.string.emailnotvalid));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(mSignUpEmail);
        }

        if (TextUtils.isEmpty(mSignUpPassword.getEditText().getText())) {
            TextInputUtil.setError(mSignUpPassword, getString(R.string.passwordempty));
            isValid = false;
        } else if (!(mSignUpPassword.getEditText().getText().toString().length() >= 6)) {
            TextInputUtil.setError(mSignUpPassword, getString(R.string.passwordmustbe));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(mSignUpPassword);
        }

        if (TextUtils.isEmpty(mSignUpConfirmPassword.getEditText().getText())) {
            TextInputUtil.setError(mSignUpConfirmPassword, getString(R.string.confirm_passwordempty));
            isValid = false;
        } else {
            TextInputUtil.setDisableError(mSignUpConfirmPassword);
        }

        if (!mSignUpPassword.getEditText().getText().toString().equalsIgnoreCase(mSignUpConfirmPassword.getEditText().getText().toString())) {

            TextInputUtil.setError(mSignUpConfirmPassword, getString(R.string.notmatch));
            showToast(getString(R.string.notmatch));
            isValid = false;

        }

        return isValid;

    }

    @Override
    public void onValidationError(APIError apiError) {

//        loge(new Gson().toJson(apiError));
//        try {
//
//
//            if (apiError.getData() != null) {
//
//
//                APIError.Data data = new Gson().fromJson(new Gson().toJson(apiError.getData()), APIError.Data.class);
//
//
//                if (data.getName() != null) {
//                    TextInputUtil.setError(mSignUpName, data.getName().get(0));
//                }
//
//                if (data.getMobileNo() != null) {
//                    TextInputUtil.setError(mSignUpMobile, data.getMobileNo().get(0));
//                }
//
//                if (data.getPassword() != null) {
//                    TextInputUtil.setError(mSignUpPassword, data.getPassword().get(0));
//                }
//
//
//            } else {
//                Toast.makeText(this, apiError.getMessage() + "", Toast.LENGTH_SHORT).show();
//
//            }
////
//        } catch (ClassCastException e) {
//
//            showToast("Something went wrong, Please try again later\n");
//        }


    }

    @Override
    public void onError(Throwable throwable) {

//        showToast(throwable.getMessage());

        showToast("Something went wrong");
    }

    @Override
    public void onSuccess(RegisterResponse registerResponse) {

        showToast(registerResponse.getMessage());
        finish();

    }

    @Override
    public void noNetwork() {

        super.onNetworkFailure();
        showToast(getString(R.string.nointernet));

    }

    @Override
    public void showOTPCode(String code) {

        otpInput.getEditText().setText(code);
        please.setVisibility(View.GONE);

    }
}
