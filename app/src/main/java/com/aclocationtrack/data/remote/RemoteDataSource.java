package com.aclocationtrack.data.remote;

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

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.HeaderMap;
import retrofit2.http.Url;

/**
 * Created by yasar on 22/3/18.
 */

public interface RemoteDataSource extends ApiService{


}
