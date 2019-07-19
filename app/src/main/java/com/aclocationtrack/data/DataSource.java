package com.aclocationtrack.data;


import com.aclocationtrack.data.local.LocalDataSource;
import com.aclocationtrack.data.pref.Pref;
import com.aclocationtrack.data.remote.RemoteDataSource;

/**
 * Created by yasar on 6/3/18.
 */

public interface DataSource extends RemoteDataSource, LocalDataSource, Pref {


}
