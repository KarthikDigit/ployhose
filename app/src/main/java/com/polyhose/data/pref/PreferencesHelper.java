package com.polyhose.data.pref;

import android.content.Context;

/**
 * Created by yasar on 7/3/18.
 */

public class PreferencesHelper implements Pref {


    private static PreferencesHelper preferences;

    private UserPreferences sharedPreferences;


    private PreferencesHelper(Context context) {

        sharedPreferences = new UserPreferences(context, PREF_NAME);

    }

    public static PreferencesHelper getPreferencesInstance(Context context) {

        if (preferences == null) {
            preferences = new PreferencesHelper(context);
        }

        return preferences;
    }

    @Override
    public void saveApiKey(String apiKey) {

        sharedPreferences.setString(KEY_API_KEY, apiKey);
    }

    @Override
    public String getApiKey() {

        return sharedPreferences.getString(KEY_API_KEY, null);
    }

    @Override
    public void saveUserName(String userName) {

        sharedPreferences.setString(KEY_USER_NAME, userName);
    }

    @Override
    public String getUserName() {
        return sharedPreferences.getString(KEY_USER_NAME, null);
    }

    @Override
    public void saveRoleName(String roleName) {
        sharedPreferences.setString(KEY_ROLE_NAME, roleName);
    }

    @Override
    public String getRolerName() {
        return sharedPreferences.getString(KEY_ROLE_NAME, null);
    }

    @Override
    public void saveRoleId(String roleId) {
        sharedPreferences.setString(KEY_ROLE_ID, roleId);
    }

    @Override
    public String getRoleId() {
        return sharedPreferences.getString(KEY_ROLE_ID, null);
    }

    @Override
    public void saveRegionName(String region_name) {

        sharedPreferences.setString(KEY_REGION_NAME, region_name);
    }

    @Override
    public String getRegionName() {
        return sharedPreferences.getString(KEY_REGION_NAME, null);
    }

    @Override
    public void saveUserId(String userId) {
        sharedPreferences.setString(KEY_USER_ID, userId);
    }

    @Override
    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, null);
    }

    @Override
    public void saveRegionId(String regionId) {

        sharedPreferences.setString(KEY_REGION_ID, regionId);
    }

    @Override
    public String getRegionId() {
        return sharedPreferences.getString(KEY_REGION_ID, null);
    }

    @Override
    public void saveCurrentDate(String d) {

        sharedPreferences.setString(KEY_CURRENTDATE, d);
    }

    @Override
    public String getCurrentDate() {
        return sharedPreferences.getString(KEY_CURRENTDATE, null);
    }

    @Override
    public void saveFcmToken(String fcmToken) {

        sharedPreferences.setString(KEY_FCM_TOKEN, fcmToken);
    }

    @Override
    public String getFcmToken() {
        return sharedPreferences.getString(KEY_FCM_TOKEN, null);
    }

    @Override
    public void saveDeviceId(String deviceId) {

        sharedPreferences.setString(KEY_DEVICE_ID, deviceId);
    }

    @Override
    public String getDeviceId() {
        return sharedPreferences.getString(KEY_DEVICE_ID, null);
    }

    @Override
    public void punchEnabled(boolean isEnabled) {
        sharedPreferences.setBoolean(KEY_PUNCH_ENABLED, isEnabled);

    }

    @Override
    public boolean isPunchEnabled() {
        return sharedPreferences.getBoolean(KEY_PUNCH_ENABLED, false);
    }

    @Override
    public void saveIsLogin(boolean isLogin) {

        sharedPreferences.setBoolean(KEY_LOGIN_STATUS, isLogin);
    }

    @Override
    public boolean isLoggedIn() {
        return sharedPreferences.getBoolean(KEY_LOGIN_STATUS, false);
    }

    @Override
    public void clear() {

        sharedPreferences.clearPreference();

    }

    @Override
    public void savePush(boolean isNoti) {
        sharedPreferences.setBoolean(KEY_ISPUSH_NOTIFICATION, isNoti);
    }

    @Override
    public boolean getPush() {
        return sharedPreferences.getBoolean(KEY_ISPUSH_NOTIFICATION, false);
    }

    @Override
    public void saveTaskAdded(boolean isTaskAdd) {

        sharedPreferences.setBoolean(KEY_TASK_ADDED, isTaskAdd);
    }

    @Override
    public boolean getTaskaAdded() {
        return sharedPreferences.getBoolean(KEY_TASK_ADDED, false);
    }

    @Override
    public void saveCustomerAdded(boolean isCustomerAdded) {

        sharedPreferences.setBoolean(KEY_CUSTOMER_ADDED, isCustomerAdded);
    }

    @Override
    public boolean getCustomerAdded() {
        return sharedPreferences.getBoolean(KEY_CUSTOMER_ADDED, false);
    }


}
