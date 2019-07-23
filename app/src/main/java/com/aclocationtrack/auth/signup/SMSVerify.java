package com.aclocationtrack.auth.signup;

import android.content.Context;

//import com.sinch.verification.Config;
//import com.sinch.verification.SinchVerification;
//import com.sinch.verification.Verification;

public class SMSVerify {

//    private static final String APPLICATION_KEY = "34228828-4486-4a3b-871c-f86ce5630439";
//    private Verification mVerification;
//    private boolean mIsSmsVerification;
//    private boolean mShouldFallback = true;
//    private boolean mIsVerified;
//
//    private Config config;

    public SMSVerify(Context context) {

//
//        config = SinchVerification.config()
//                .applicationKey(APPLICATION_KEY)
//                .context(context)
//                .build();

    }


    public void sendSmsAndVerify(String mobilenumber) {


//        VerificationListener listener = new MyVerificationListener();

        String mPhoneNumber = "+91" + mobilenumber;
//        mVerification = SinchVerification.createSmsVerification(config, mPhoneNumber, listener);
//        mVerification.initiate();

    }

//
//    public class MyVerificationListener implements VerificationListener {
//
//        @Override
//        public void onInitiated(InitiationResult result) {
//            Log.d(TAG, "Initialized!");
//            showProgress();
//        }
//
//        @Override
//        public void onInitiationFailed(Exception exception) {
//            Log.e(TAG, "Verification initialization failed: " + exception.getMessage());
//
//            hideProgress();
//
//            if (exception instanceof InvalidInputException) {
//                // Incorrect number provided
//            } else if (exception instanceof ServiceErrorException) {
//                // Verification initiation aborted due to early reject feature,
//                // client callback denial, or some other Sinch service error.
//                // Fallback to other verification method here.
//                fallbackIfNecessary();
//            } else {
//                // Other system error, such as UnknownHostException in case of network error
//            }
//        }
//
//        @Override
//        public void onVerificationFallback() {
//            fallbackIfNecessary();
//        }
//
//        private void fallbackIfNecessary() {
////            if (mShouldFallback) {
//////                mIsSmsVerification = !mIsSmsVerification;
////
////                Log.i(TAG, "Falling back to sms verification.");
////
////                mShouldFallback = false;
////                requestPermissions(); // Initiate verification with the alternative method.
////            }
//        }
//
//        @Override
//        public void onVerified() {
//            mIsVerified = true;
//            Log.d(TAG, "Verified!");
//            hideProgress();
//            showCompleted();
//        }
//
//        @Override
//        public void onVerificationFailed(Exception exception) {
//            if (mIsVerified) {
//                return;
//            }
//
//
//            Log.e(TAG, "Verification failed: " + exception.getMessage());
//            if (exception instanceof CodeInterceptionException) {
//                // Automatic code interception failed, probably due to missing permissions.
//                // Let the user try and enter the code manually.
//                hideProgress();
//            } else {
//                Log.e(TAG, "Verification initialization failed: " + exception.getMessage());
//                hideProgress();
//            }
//
//            enableInputField(true);
//        }
//    }
}
