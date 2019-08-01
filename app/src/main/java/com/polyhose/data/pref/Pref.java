package com.polyhose.data.pref;


/**
 * Created by yasar on 22/3/18.
 */

public interface Pref {


    String PREF_NAME = "pref_polyhose";

    String KEY_ROLE_NAME = "role_Name";
    String KEY_LOGIN_STATUS = "login_status";
    String KEY_API_KEY = "api_key";
    String KEY_ROLE_ID = "role_id";
    String KEY_REGION_ID = "region_id";
    String KEY_USER_ID = "user_id";
    String KEY_USER_NAME = "userName";
    String KEY_FCM_TOKEN = "fcmtoken";
    String KEY_DEVICE_ID = "deviceid";
    String KEY_ISPUSH_NOTIFICATION = "pushnotification";
    String KEY_CURRENTDATE = "currentdate";
    String KEY_PUNCH_ENABLED = "punch_enabled";
    String KEY_REGION_NAME = "region_name";
    String KEY_TASK_ADDED = "task_added";
    String KEY_CUSTOMER_ADDED = "customer_added";


    void saveApiKey(String apiKey);

    String getApiKey();

    void saveUserName(String userName);

    String getUserName();

    void saveRoleName(String roleName);

    String getRolerName();

    void saveRoleId(String roleId);

    String getRoleId();

    void saveRegionName(String region_name);

    String getRegionName();

    void saveUserId(String userId);

    String getUserId();

    void saveRegionId(String regionId);

    String getRegionId();

    void saveCurrentDate(String d);

    String getCurrentDate();

    void saveFcmToken(String fcmToken);

    String getFcmToken();

    void saveDeviceId(String deviceId);

    String getDeviceId();

    void punchEnabled(boolean isEnabled);

    boolean isPunchEnabled();

    void saveIsLogin(boolean isLogin);

    boolean isLoggedIn();

    void savePush(boolean isNoti);

    boolean getPush();

    void saveTaskAdded(boolean isTaskAdd);

    boolean getTaskaAdded();

    void saveCustomerAdded(boolean isCustomerAdded);

    boolean getCustomerAdded();

    void clear();

}
