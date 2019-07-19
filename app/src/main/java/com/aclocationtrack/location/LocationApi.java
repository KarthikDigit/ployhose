package com.aclocationtrack.location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Location;
import android.support.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResponse;
import com.google.android.gms.location.SettingsClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

public class LocationApi extends LocationCallback implements OnSuccessListener<Location>, OnFailureListener {

    private static final int KEY_INTERVEL = 10000;
    private static final int KEY_FASTEST_INTERVEL = 10000;
    private static final int KEY_LOCATION_PRIORITY = LocationRequest.PRIORITY_HIGH_ACCURACY;
    private Context mContext;
    private FusedLocationProviderClient fusedLocationClient;
    private LocationRequest locationRequest;
    private LocationCallBackListener locationCallBackListener;


    public LocationApi(Context mContext) {

        this.mContext = mContext;
        locationRequest = LocationRequest.create();

        locationRequest.setInterval(KEY_INTERVEL);
        locationRequest.setFastestInterval(KEY_FASTEST_INTERVEL);
        locationRequest.setPriority(KEY_LOCATION_PRIORITY);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.mContext);

    }

    public LocationApi(Context mContext, LocationCallBackListener locationCallBackListener) {

        this.mContext = mContext;
        this.locationCallBackListener = locationCallBackListener;
        locationRequest = LocationRequest.create();
        locationRequest.setInterval(KEY_INTERVEL);
        locationRequest.setFastestInterval(KEY_FASTEST_INTERVEL);
        locationRequest.setPriority(KEY_LOCATION_PRIORITY);
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this.mContext);

    }

    public void setLocationCallBackListener(LocationCallBackListener locationCallBackListener) {
        this.locationCallBackListener = locationCallBackListener;
    }

    @SuppressLint("MissingPermission")
    public void getLastLocation() {
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this).addOnFailureListener(this);
    }

    @SuppressLint("MissingPermission")
    public void requestLocationUpdates() {

        fusedLocationClient.requestLocationUpdates(locationRequest,
                this,
                null /* Looper */);
    }

    public void getCurrentLocationSettings() {

        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);

        SettingsClient client = LocationServices.getSettingsClient(mContext);
        Task<LocationSettingsResponse> task = client.checkLocationSettings(builder.build());


        task.addOnSuccessListener(new OnSuccessListener<LocationSettingsResponse>() {
            @Override
            public void onSuccess(LocationSettingsResponse locationSettingsResponse) {

                getLastLocation();
                requestLocationUpdates();
            }
        });

        task.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

                if (check()) locationCallBackListener.onLocationFail(e);

            }
        });

    }

    public void stopLocationUpdates() {
        fusedLocationClient.removeLocationUpdates(this);
    }

    @Override
    public void onLocationResult(LocationResult locationResult) {
        super.onLocationResult(locationResult);

        if (locationResult == null) {
            if (check())
                locationCallBackListener.onLocationFail(new Exception("No Location updates"));
            return;
        }
        for (Location location : locationResult.getLocations()) {

            if (location != null) {

                if (check()) locationCallBackListener.onLocationSuccess(location);
            } else {
                if (check()) locationCallBackListener.onLocationFail(new Exception("No Location"));
            }
        }
    }

    @Override
    public void onSuccess(Location location) {

        if (location != null) {

            if (check()) locationCallBackListener.onLocationSuccess(location);
        } else {
            if (check()) locationCallBackListener.onLocationFail(new Exception("No Location"));
        }
    }

    @Override
    public void onFailure(@NonNull Exception e) {

        if (check()) locationCallBackListener.onLocationFail(e);
    }


    private boolean check() {

        return locationCallBackListener != null;
    }

    public interface LocationCallBackListener {

        void onLocationSuccess(Location location);

        void onLocationFail(Exception e);

    }
}
