package com.aclocationtrack.data.remote;

import android.util.Log;

import com.aclocationtrack.data.listener.DataListener;
import com.aclocationtrack.data.model.ResetPassword;
import com.aclocationtrack.data.model.request.Deviceinfo;
import com.aclocationtrack.data.model.request.OrderRequest;
import com.aclocationtrack.data.model.request.StockProductInfoReq;
import com.aclocationtrack.data.model.request.StockProductSearchReq;
import com.aclocationtrack.data.model.request.TaskPost;
import com.aclocationtrack.data.model.request.UpdateProfile;
import com.aclocationtrack.data.model.response.ProductSearch;
import com.aclocationtrack.data.model.response.StockProductSearch;

import java.util.Map;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.HeaderMap;

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
    public void login(String json, final DataListener dataListener) {

        Log.e(TAG, "login: " + json);

        apiService.login(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void mobileValidate(String json, DataListener dataListener) {
        apiService.mobileValidate(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));

    }

    @Override
    public void getUserProfile(@HeaderMap Map<String, String> map, DataListener dataListener) {

        Log.e(TAG, "getStudentProfile: " + map);

        apiService.getUserProfile(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));

    }

    @Override
    public void register(String json, DataListener dataListener) {

        apiService.register(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void createOrder(@HeaderMap Map<String, String> map, DataListener dataListener) {

        apiService.createOrder(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void createOrderForDealer(Map<String, String> map, DataListener dataListener) {
        apiService.createOrderForDealer(map).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void searchProduc(String json, DataListener dataListener) {
        apiService.searchProduct(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public Call<ProductSearch> searchProductDirect(@HeaderMap Map<String, String> headermap, String json) {
        return apiService.searchProductDirect(headermap, json);
    }

    @Override
    public void getProductInfo(Map<String, String> headermap, String json, DataListener dataListener) {

        apiService.getProductInfo(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));

    }

    @Override
    public void saveCustomerOrder(Map<String, String> headermap, OrderRequest json, DataListener dataListener) {
        apiService.saveCustomerOrder(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void loadMyOrder(Map<String, String> headermap, String page, String status, DataListener dataListener) {
        apiService.loadMyOrder(headermap, page, status).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void getOrderDetails(Map<String, String> headermap, int page, DataListener dataListener) {
        apiService.getOrderDetails(headermap, page).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public Call<StockProductSearch> stockProductSearch(Map<String, String> headermap, StockProductSearchReq json) {
        return apiService.stockProductSearch(headermap, json);
    }

    @Override
    public void stockProductInfo(Map<String, String> headermap, StockProductInfoReq json, DataListener dataListener) {
        apiService.stockProductInfo(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void loadAnnouncement(Map<String, String> headermap, DataListener dataListener) {
        apiService.loadAnnouncement(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void loadAnnouncementDetails(Map<String, String> headermap, int id, DataListener dataListener) {
        apiService.loadAnnouncementDetails(headermap, id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void loadDownloads(Map<String, String> headermap, DataListener dataListener) {
        apiService.loadDownloads(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public Call<ResponseBody> downloadFileWithDynamicUrlAsync(String fileUrl) {
        return apiService.downloadFileWithDynamicUrlAsync(fileUrl);
    }

    @Override
    public void createService(Map<String, String> headermap, DataListener dataListener) {

        apiService.createService(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void postService(Map<String, String> headermap, String json, DataListener dataListener) {
        apiService.postService(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void getAllServicesForCustomer(Map<String, String> headermap, int page, int status, DataListener dataListener) {
        apiService.getAllServicesForCustomer(headermap, page, status).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void getServiceDetailsForCustomer(Map<String, String> headermap, int id, DataListener dataListener) {

        apiService.getServiceDetailsForCustomer(headermap, id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));

    }

    @Override
    public void getServiceDetailsForServiceMan(Map<String, String> headermap, int id, DataListener dataListener) {

        apiService.getServiceDetailsForServiceMan(headermap, id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));

    }

    @Override
    public void changeServiceManStatus(Map<String, String> headermap, int id, String json, DataListener dataListener) {
        apiService.changeServiceManStatus(headermap, id, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void postComments(Map<String, String> headermap, String json, DataListener dataListener) {
        apiService.postComments(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void getTask(Map<String, String> headermap, DataListener dataListener) {
        apiService.getTask(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void postTask(Map<String, String> headermap, TaskPost json, DataListener dataListener) {
        apiService.postTask(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void getAllTask(Map<String, String> headermap, int page, int status, DataListener dataListener) {
        apiService.getAllTask(headermap, page, status).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void getTaskDetails(Map<String, String> headermap, int id, DataListener dataListener) {
        apiService.getTaskDetails(headermap, id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void postDeviceInfo(Map<String, String> headermap, Deviceinfo json, DataListener dataListener) {
        apiService.postDeviceInfo(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void logout(Map<String, String> headermap, DataListener dataListener) {
        apiService.logout(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void getProfile(Map<String, String> headermap, DataListener dataListener) {

        apiService.getProfile(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void updateProfile(Map<String, String> headermap, UpdateProfile json, DataListener dataListener) {

        apiService.updateProfile(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));

    }

    @Override
    public void changePassword(ResetPassword json, DataListener dataListener) {

        apiService.resetPassword(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void generateOtp(String json, DataListener dataListener) {
        apiService.generateOtp(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void validateOtp(String json, DataListener dataListener) {
        apiService.validateOtp(json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void taskStatusUpdate(@HeaderMap Map<String, String> headermap, String json, DataListener dataListener) {
        apiService.taskStatusUpdate(headermap, json).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    @Override
    public void fetchAllBrand(@HeaderMap Map<String, String> headermap, int category_id, DataListener dataListener) {
        apiService.fetchAllBrand(headermap, category_id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(observer(dataListener));
    }

    private static <T> Observer<T> observer(final DataListener dataListener) {

        return new Observer<T>() {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(T value) {

                dataListener.onSuccess(value);

            }

            @Override
            public void onError(Throwable e) {


                Log.e(TAG, "onError: Handle for testing purpose " + e.getMessage());


                dataListener.onFail(e);
            }

            @Override
            public void onComplete() {

//                Log.e(TAG, "onComplete:  qqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqqq");

            }
        };

    }


}
