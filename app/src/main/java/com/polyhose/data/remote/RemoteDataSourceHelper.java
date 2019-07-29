package com.polyhose.data.remote;

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

import java.util.List;
import java.util.Map;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yasar on 6/3/18.
 */

public class RemoteDataSourceHelper implements RemoteDataSource {

    private static final String TAG = "RemoteDataSourceHelper";

    private ApiService apiService;

    public RemoteDataSourceHelper(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Observable<LoginApiResponse> login(LoginRequest json) {
        return apiService.login(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<UserDetailsResponse> getUserProfile(Map<String, String> headermap) {
        return apiService.getUserProfile(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<PunchInResponse> punchIN(PunchIn punchIn) {
        return apiService.punchIN(punchIn).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Observable<PunchInResponse> punchOUT(PunchOut punchOut) {
        return apiService.punchOUT(punchOut).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<CustomerDetailsResponse> postCustomer(CustomerDetailsRequest customerDetailsRequest) {
        return apiService.postCustomer(customerDetailsRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<CustomerDetailsResponse> updateCustomer(CustomerDetailsRequest updateCustomerDetailsRequest) {
        return apiService.updateCustomer(updateCustomerDetailsRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<CustomerType>> getCustomerType(String apiKey) {
        return apiService.getCustomerType(apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<CompanyType>> getCompanyType(String apiKey) {
        return apiService.getCompanyType(apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<IndustrialType>> getIndustrialType(String apiKey) {
        return apiService.getIndustrialType(apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Region>> getAllRegion(String apiKey) {
        return apiService.getAllRegion(apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<States>> getAllStates(String regionId, String apiKey) {
        return apiService.getAllStates(regionId, apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Attendance>> getAllAttendance(String userId, String apiKey) {
        return apiService.getAllAttendance(userId, apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Observable<TaskResponse> postTask(TaskRequest taskRequest) {
        return apiService.postTask(taskRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Observable<List<Task>> getAllTasks(String userId, String apiKey) {
        return apiService.getAllTasks(userId, apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Observable<List<Customers>> getAllCustomers(CustomerRequest customerRequest) {
        return apiService.getAllCustomers(customerRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Observable<List<Customer>> getCustomerById(String customerId, String apiKey) {
        return apiService.getCustomerById(customerId, apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

    }

    @Override
    public Observable<List<ContactPerson>> getAllContactPerson(ContactPersonRequest request) {
        return apiService.getAllContactPerson(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Anouncement>> getAllAnnouncement(String roleId, String regionId, String apiKey) {
        return apiService.getAllAnnouncement(roleId, regionId, apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<AnouncementDetails>> getAnnouncementById(AnnouncemnetDetailRequest request) {
        return apiService.getAnnouncementById(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<List<Downloads>> getAllDownloads(String apiKey) {
        return apiService.getAllDownloads(apiKey).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> postDeviceInfo(Map<String, String> headermap, Deviceinfo json) {
        return apiService.postDeviceInfo(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<String> logout(LogoutRequest request) {
        return apiService.logout(request).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<ProfileDetails> getProfile(Map<String, String> headermap) {
        return apiService.getProfile(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

//    @Override
//    public void mobileValidate(String json, DataListener dataListener) {
//        apiService.mobileValidate(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//
//    }
//
//    @Override
//    public void getUserProfile(@HeaderMap Map<String, String> map, DataListener dataListener) {
//
//        Log.e(TAG, "getStudentProfile: " + map);
//
//        apiService.getUserProfile(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//
//    }
//
//    @Override
//    public void register(String json, DataListener dataListener) {
//
//        apiService.register(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void createOrder(@HeaderMap Map<String, String> map, DataListener dataListener) {
//
//        apiService.createOrder(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void createOrderForDealer(Map<String, String> map, DataListener dataListener) {
//        apiService.createOrderForDealer(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void searchProduc(String json, DataListener dataListener) {
//        apiService.searchProduct(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public Call<ProductSearch> searchProductDirect(@HeaderMap Map<String, String> headermap, String json) {
//        return apiService.searchProductDirect(headermap, json);
//    }
//
//    @Override
//    public void getProductInfo(Map<String, String> headermap, String json, DataListener dataListener) {
//
//        apiService.getProductInfo(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//
//    }
//
//    @Override
//    public void saveCustomerOrder(Map<String, String> headermap, OrderRequest json, DataListener dataListener) {
//        apiService.saveCustomerOrder(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void loadMyOrder(Map<String, String> headermap, String page, String status, DataListener dataListener) {
//        apiService.loadMyOrder(headermap, page, status).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void getOrderDetails(Map<String, String> headermap, int page, DataListener dataListener) {
//        apiService.getOrderDetails(headermap, page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public Call<StockProductSearch> stockProductSearch(Map<String, String> headermap, StockProductSearchReq json) {
//        return apiService.stockProductSearch(headermap, json);
//    }
//
//    @Override
//    public void stockProductInfo(Map<String, String> headermap, StockProductInfoReq json, DataListener dataListener) {
//        apiService.stockProductInfo(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void loadAnnouncement(Map<String, String> headermap, DataListener dataListener) {
//        apiService.loadAnnouncement(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void loadAnnouncementDetails(Map<String, String> headermap, int id, DataListener dataListener) {
//        apiService.loadAnnouncementDetails(headermap, id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void loadDownloads(Map<String, String> headermap, DataListener dataListener) {
//        apiService.loadDownloads(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public Call<ResponseBody> downloadFileWithDynamicUrlAsync(String fileUrl) {
//        return apiService.downloadFileWithDynamicUrlAsync(fileUrl);
//    }
//
//    @Override
//    public void createService(Map<String, String> headermap, DataListener dataListener) {
//
//        apiService.createService(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void postService(Map<String, String> headermap, String json, DataListener dataListener) {
//        apiService.postService(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void getAllServicesForCustomer(Map<String, String> headermap, int page, int status, DataListener dataListener) {
//        apiService.getAllServicesForCustomer(headermap, page, status).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void getServiceDetailsForCustomer(Map<String, String> headermap, int id, DataListener dataListener) {
//
//        apiService.getServiceDetailsForCustomer(headermap, id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//
//    }
//
//    @Override
//    public void getServiceDetailsForServiceMan(Map<String, String> headermap, int id, DataListener dataListener) {
//
//        apiService.getServiceDetailsForServiceMan(headermap, id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//
//    }
//
//    @Override
//    public void changeServiceManStatus(Map<String, String> headermap, int id, String json, DataListener dataListener) {
//        apiService.changeServiceManStatus(headermap, id, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void postComments(Map<String, String> headermap, String json, DataListener dataListener) {
//        apiService.postComments(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void getTask(Map<String, String> headermap, DataListener dataListener) {
//        apiService.getTask(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void postTask(Map<String, String> headermap, TaskPost json, DataListener dataListener) {
//        apiService.postTask(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void getAllTask(Map<String, String> headermap, int page, int status, DataListener dataListener) {
//        apiService.getAllTask(headermap, page, status).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void getTaskDetails(Map<String, String> headermap, int id, DataListener dataListener) {
//        apiService.getTaskDetails(headermap, id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void postDeviceInfo(Map<String, String> headermap, Deviceinfo json, DataListener dataListener) {
//        apiService.postDeviceInfo(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void logout(Map<String, String> headermap, DataListener dataListener) {
//        apiService.logout(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void getProfile(Map<String, String> headermap, DataListener dataListener) {
//
//        apiService.getProfile(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void updateProfile(Map<String, String> headermap, UpdateProfile json, DataListener dataListener) {
//
//        apiService.updateProfile(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//
//    }
//
//    @Override
//    public void changePassword(ResetPassword json, DataListener dataListener) {
//
//        apiService.resetPassword(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void generateOtp(String json, DataListener dataListener) {
//        apiService.generateOtp(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void validateOtp(String json, DataListener dataListener) {
//        apiService.validateOtp(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void taskStatusUpdate(@HeaderMap Map<String, String> headermap, String json, DataListener dataListener) {
//        apiService.taskStatusUpdate(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    @Override
//    public void fetchAllBrand(@HeaderMap Map<String, String> headermap, int category_id, DataListener dataListener) {
//        apiService.fetchAllBrand(headermap, category_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
//    }
//
//    private static <T> Observer<T> observer(final DataListener dataListener) {
//
//        return new Observer<T>() {
//
//            @Override
//            public void onSubscribe(Disposable d) {
//
//            }
//
//            @Override
//            public void onNext(T value) {
//
//                dataListener.onSuccess(value);
//
//            }
//
//            @Override
//            public void onError(Throwable e) {
//
//
//                Log.e(TAG, "onError: Handle for testing purpose " + e.getMessage());
//
//
//                dataListener.onFail(e);
//            }
//
//            @Override
//            public void onComplete() {
//
////                Log.e(TAG, "onComplete:  qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");
//
//            }
//        };
//
//    }


}
