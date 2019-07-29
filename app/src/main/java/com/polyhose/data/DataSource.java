package com.polyhose.data;


import com.polyhose.data.local.LocalDataSource;
import com.polyhose.data.pref.Pref;
import com.polyhose.data.remote.RemoteDataSource;

/**
 * Created by yasar on 6/3/18.
 */

public interface DataSource extends RemoteDataSource, LocalDataSource, Pref {



    void saveSession(String userName,String apiKey,String userId,String roleName,String roleId,String regionId);



}
