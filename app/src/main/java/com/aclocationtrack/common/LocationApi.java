package com.aclocationtrack.common;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;

public class LocationApi {

    /**
     * Code used in requesting runtime permissions.
     */
    private static final int REQUEST_PERMISSIONS_REQUEST_CODE = 34;

    /**
     * Constant used in the location settings dialog.
     */
    private static final int REQUEST_CHECK_SETTINGS = 0x1;

    /**
     * The desired interval for location updates. Inexact. Updates may be more or less frequent.
     */
    private static final long UPDATE_INTERVAL_IN_MILLISECONDS = 1000;

    /**
     * The fastest rate for active location updates. Exact. Updates will never be more frequent
     * than this value.
     */
    private static final long FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS =
            UPDATE_INTERVAL_IN_MILLISECONDS / 2;

    // Keys for storing activity state in the Bundle.
    private final static String KEY_REQUESTING_LOCATION_UPDATES = "requesting-location-updates";
    private final static String KEY_LOCATION = "location";
    private final static String KEY_LAST_UPDATED_TIME_STRING = "last-updated-time-string";
    private FusedLocationProviderClient mFusedLocationClient;
    private Context mContext;
    private LocationApiCallBack locationApiCallBack;
    private LocationRequest mLocationRequest;

    private static Location mLocation;

    public LocationApi(Context context) {
        mFusedLocationClient = new FusedLocationProviderClient(context);
        this.mContext = context;

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(UPDATE_INTERVAL_IN_MILLISECONDS);

        // Sets the fastest rate for active location updates. This interval is exact, and your
        // application will never receive updates faster than this value.
        mLocationRequest.setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS);

        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        // Create LocationSettingsRequest object using location request
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();

        // Check whether location settings are satisfied
        // https://developers.google.com/android/reference/com/google/android/gms/location/SettingsClient
        SettingsClient settingsClient = LocationServices.getSettingsClient(mContext);
        settingsClient.checkLocationSettings(locationSettingsRequest);

        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, locationCallback, Looper.myLooper());


    }

//    private void setLocationApiCallBack(LocationApiCallBack locationApiCallBack) {
//        this.locationApiCallBack = locationApiCallBack;
//    }

    public void getLastLocation(final LocationApiCallBack locationApiCallBack) {

//        setLocationApiCallBack(locationApiCallBack);


        if (isLocationServicesAvailable(mContext)) {

            if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                locationApiCallBack.needPermission();

                return;
            }


            if (mLocation != null) {

                mFusedLocationClient.getLastLocation().addOnFailureListener(new OnFailure(locationApiCallBack))
                        .addOnCompleteListener(new OnComplelistener(mLocation, locationApiCallBack));

            } else {

                mFusedLocationClient.requestLocationUpdates(mLocationRequest, locationCallback, Looper.myLooper());

                mFusedLocationClient.getLastLocation().addOnFailureListener(new OnFailure(locationApiCallBack))
                        .addOnCompleteListener(new OnComplelistener(mLocation, locationApiCallBack));

            }

        } else {

            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Location Enable");  // GPS not found
            builder.setMessage("Please enable loaction to proceed"); // Want to enable?
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    locationApiCallBack.enableLocation();
                    mContext.startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    locationApiCallBack.enableLocation();
                }
            });
            builder.create().show();

            return;
        }

    }


    private static class OnComplelistener implements OnCompleteListener<Location> {

        private LocationApiCallBack locationApiCallBack;

        private OnComplelistener(Location location, LocationApiCallBack locationApiCallBack) {
            this.locationApiCallBack = locationApiCallBack;
        }

        @Override
        public void onComplete(@NonNull Task<Location> task) {
            if (task.isSuccessful() && task.getResult() != null) {

                mLocation = task.getResult();

                locationApiCallBack.onComplete(task.getResult());


            } else {

                locationApiCallBack.onFailure(task.getException());
            }
        }
    }


    private static class OnFailure implements OnFailureListener {

        private LocationApiCallBack locationApiCallBack;


        protected OnFailure(LocationApiCallBack locationApiCallBack) {
            this.locationApiCallBack = locationApiCallBack;
        }


        @Override
        public void onFailure(@NonNull Exception e) {
            locationApiCallBack.onFailure(e);
        }
    }


    private LocationCallback locationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            super.onLocationResult(locationResult);
            mLocation = locationResult.getLastLocation();
        }
    };

    public void stopLocationUpdates() {
        mFusedLocationClient.removeLocationUpdates(locationCallback);
    }

    public static boolean isLocationServicesAvailable(Context context) {
        int locationMode = 0;
        String locationProviders;
        boolean isAvailable = false;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            try {
                locationMode = Settings.Secure.getInt(context.getContentResolver(), Settings.Secure.LOCATION_MODE);
            } catch (Settings.SettingNotFoundException e) {
                e.printStackTrace();
            }

            isAvailable = (locationMode != Settings.Secure.LOCATION_MODE_OFF);
        } else {
            locationProviders = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.LOCATION_PROVIDERS_ALLOWED);
            isAvailable = !TextUtils.isEmpty(locationProviders);
        }

        boolean coarsePermissionCheck = (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED);
        boolean finePermissionCheck = (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED);

        return isAvailable && (coarsePermissionCheck || finePermissionCheck);
    }

    public interface LocationApiCallBack {

        void onComplete(Location location);

        void onFailure(Exception e);

        void needPermission();

        void enableLocation();

    }
}