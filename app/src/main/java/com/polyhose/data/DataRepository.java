package com.polyhose.data;

import android.content.Context;


import com.polyhose.data.model.ProfileDetails;
import com.polyhose.data.model.request.AnnouncemnetDetailRequest;
import com.polyhose.data.model.request.ContactPersonRequest;
import com.polyhose.data.model.request.CustomerDetailsRequest;
import com.polyhose.data.model.request.CustomerRequest;
import com.polyhose.data.model.request.Deviceinfo;
import com.polyhose.data.model.request.LoginRequest;
import com.polyhose.data.model.request.LogoutRequest;
import com.polyhose.data.model.request.PunchIn;
import com.polyhose.data.model.request.PunchOut;
import com.polyhose.data.model.request.TaskRequest;
import com.polyhose.data.model.request.UpdateCustomerDetailsRequest;
import com.polyhose.data.model.response.Anouncement;
import com.polyhose.data.model.response.AnouncementDetails;
import com.polyhose.data.model.response.Attendance;
import com.polyhose.data.model.response.CompanyType;
import com.polyhose.data.model.response.ContactPerson;
import com.polyhose.data.model.response.Customer;
import com.polyhose.data.model.response.CustomerDetailsResponse;
import com.polyhose.data.model.response.CustomerType;
import com.polyhose.data.model.response.Customers;
import com.polyhose.data.model.response.Downloads;
import com.polyhose.data.model.response.IndustrialType;
import com.polyhose.data.model.response.LoginApiResponse;
import com.polyhose.data.model.response.PunchInResponse;
import com.polyhose.data.model.response.Region;
import com.polyhose.data.model.response.States;
import com.polyhose.data.model.response.Task;
import com.polyhose.data.model.response.TaskResponse;
import com.polyhose.data.model.response.UserDetailsResponse;
import com.polyhose.data.pref.Pref;
import com.polyhose.data.remote.RemoteDataSource;

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by yasar on 6/3/18.
 */

public class DataRepository implements DataSource {

    private static final String TAG = "DataRepository";
    private Context context;
    private RemoteDataSource remoteDataSource;
    private Pref preferences;

    public DataRepository(Context context, RemoteDataSource remoteDataSource, Pref preferences) {
        this.context = context;
        this.remoteDataSource = remoteDataSource;
        this.preferences = preferences;
    }

    @Override
    public Observable<LoginApiResponse> login(LoginRequest json) {
        return remoteDataSource.login(json);
    }

    @Override
    public Observable<UserDetailsResponse> getUserProfile(Map<String, String> headermap) {
        return remoteDataSource.getUserProfile(headermap);
    }

    @Override
    public Observable<PunchInResponse> punchIN(PunchIn punchIn) {
        return remoteDataSource.punchIN(punchIn);
    }

    @Override
    public Observable<PunchInResponse> punchOUT(PunchOut punchOut) {
        return remoteDataSource.punchOUT(punchOut);
    }

    @Override
    public Observable<CustomerDetailsResponse> postCustomer(CustomerDetailsRequest customerDetailsRequest) {
        return remoteDataSource.postCustomer(customerDetailsRequest);
    }

    @Override
    public Observable<CustomerDetailsResponse> updateCustomer(CustomerDetailsRequest updateCustomerDetailsRequest) {
        return remoteDataSource.updateCustomer(updateCustomerDetailsRequest);
    }

    @Override
    public Observable<List<CustomerType>> getCustomerType(String apiKey) {
        return remoteDataSource.getCustomerType(apiKey);
    }

    @Override
    public Observable<List<CompanyType>> getCompanyType(String apiKey) {
        return remoteDataSource.getCompanyType(apiKey);
    }

    @Override
    public Observable<List<IndustrialType>> getIndustrialType(String apiKey) {
        return remoteDataSource.getIndustrialType(apiKey);
    }

    @Override
    public Observable<List<Region>> getAllRegion(String apiKey) {
        return remoteDataSource.getAllRegion(apiKey);
    }

    @Override
    public Observable<List<States>> getAllStates(String regionId, String apiKey) {
        return remoteDataSource.getAllStates(regionId, apiKey);
    }

    @Override
    public Observable<List<Attendance>> getAllAttendance(String userId, String apiKey) {
        return remoteDataSource.getAllAttendance(userId, apiKey);
    }

    @Override
    public Observable<TaskResponse> postTask(TaskRequest taskRequest) {
        return remoteDataSource.postTask(taskRequest);
    }

    @Override
    public Observable<List<Task>> getAllTasks(String userId, String apiKey) {
        return remoteDataSource.getAllTasks(userId, apiKey);
    }

    @Override
    public Observable<List<Customers>> getAllCustomers(CustomerRequest customerRequest) {
        return remoteDataSource.getAllCustomers(customerRequest);
    }

    @Override
    public Observable<List<Customer>> getCustomerById(String customerId, String apiKey) {
        return remoteDataSource.getCustomerById(customerId, apiKey);
    }

    @Override
    public Observable<List<ContactPerson>> getAllContactPerson(ContactPersonRequest request) {
        return remoteDataSource.getAllContactPerson(request);
    }

    @Override
    public Observable<List<Anouncement>> getAllAnnouncement(String roleId, String regionId, String apiKey) {
        return remoteDataSource.getAllAnnouncement(roleId, regionId, apiKey);
    }

    @Override
    public Observable<List<AnouncementDetails>> getAnnouncementById(AnnouncemnetDetailRequest request) {
        return remoteDataSource.getAnnouncementById(request);
    }

    @Override
    public Observable<List<Downloads>> getAllDownloads(String apiKey) {
        return remoteDataSource.getAllDownloads(apiKey);
    }

    @Override
    public Observable<String> postDeviceInfo(Map<String, String> headermap, Deviceinfo json) {
        return remoteDataSource.postDeviceInfo(headermap, json);
    }

    @Override
    public Observable<String> logout(LogoutRequest headermap) {
        return remoteDataSource.logout(headermap);
    }

    @Override
    public Observable<ProfileDetails> getProfile(Map<String, String> headermap) {
        return remoteDataSource.getProfile(headermap);
    }

    @Override
    public void saveApiKey(String apiKey) {

        preferences.saveApiKey(apiKey);
    }

    @Override
    public String getApiKey() {
        return preferences.getApiKey();
    }

    @Override
    public void saveUserName(String userName) {

        preferences.saveUserName(userName);
    }

    @Override
    public String getUserName() {
        return preferences.getUserName();
    }

    @Override
    public void saveRoleName(String roleName) {

        preferences.saveRoleName(roleName);
    }

    @Override
    public String getRolerName() {
        return preferences.getRolerName();
    }

    @Override
    public void saveRoleId(String roleId) {

        preferences.saveRoleId(roleId);
    }

    @Override
    public String getRoleId() {
        return preferences.getRoleId();
    }

    @Override
    public void saveRegionName(String region_name) {

        preferences.saveRegionName(region_name);
    }

    @Override
    public String getRegionName() {
        return preferences.getRegionName();
    }

    @Override
    public void saveUserId(String userId) {

        preferences.saveUserId(userId);
    }

    @Override
    public String getUserId() {
        return preferences.getUserId();
    }

    @Override
    public void saveRegionId(String regionId) {

        preferences.saveRegionId(regionId);
    }

    @Override
    public String getRegionId() {
        return preferences.getRegionId();
    }

    @Override
    public void saveCurrentDate(String d) {

        preferences.saveCurrentDate(d);
    }

    @Override
    public String getCurrentDate() {
        return preferences.getCurrentDate();
    }

    @Override
    public void saveFcmToken(String fcmToken) {

        preferences.saveFcmToken(fcmToken);
    }

    @Override
    public String getFcmToken() {
        return preferences.getFcmToken();
    }

    @Override
    public void saveDeviceId(String deviceId) {

        preferences.saveDeviceId(deviceId);
    }

    @Override
    public String getDeviceId() {
        return preferences.getDeviceId();
    }

    @Override
    public void punchEnabled(boolean isEnabled) {

        preferences.punchEnabled(isEnabled);
    }

    @Override
    public boolean isPunchEnabled() {
        return preferences.isPunchEnabled();
    }

    @Override
    public void saveIsLogin(boolean isLogin) {

        preferences.saveIsLogin(isLogin);
    }

    @Override
    public boolean isLoggedIn() {

        return preferences.isLoggedIn();

    }


    @Override
    public void clear() {

        preferences.clear();
    }

    @Override
    public void savePush(boolean isNoti) {
        preferences.savePush(isNoti);
    }

    @Override
    public boolean getPush() {
        return preferences.getPush();
    }

    @Override
    public void saveTaskAdded(boolean isTaskAdd) {

        preferences.saveTaskAdded(isTaskAdd);
    }

    @Override
    public boolean getTaskaAdded() {
        return preferences.getTaskaAdded();
    }

    @Override
    public void saveCustomerAdded(boolean isCustomerAdded) {

        preferences.saveCustomerAdded(isCustomerAdded);
    }

    @Override
    public boolean getCustomerAdded() {
        return preferences.getCustomerAdded();
    }


    @Override
    public void saveSession(String userName, String apiKey, String userId, String roleName, String roleId, String regionId) {

        saveUserName(userName);
        saveApiKey(apiKey);
        saveUserId(userId);
        saveRoleName(roleName);
        saveRoleId(roleId);
        saveRegionId(regionId);
        saveIsLogin(true);

    }
}

