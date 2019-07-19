package com.aclocationtrack.location;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.location.Location;
import android.os.AsyncTask;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.aclocationtrack.R;
import com.aclocationtrack.data.model.FirebaseUserLoc;
import com.aclocationtrack.data.pref.Pref;
import com.aclocationtrack.data.pref.PreferencesHelper;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Random;

public class LocationService extends Service implements LocationApi.LocationCallBackListener {


    private LocationApi locationApi;
    private Pref pref;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        createNotificationChannel();

        showNotification("");

        pref = PreferencesHelper.getPreferencesInstance(getApplicationContext());
        locationApi = new LocationApi(this, this);

    }


    private void showNotification(String msg) {

        Notification notification = new NotificationCompat.Builder(this, "NOTIFICATION_CHANNEL").setSmallIcon
                (R.mipmap.ic_launcher).setContentTitle("Location").setContentText(msg).build();

        startForeground(1001, notification);
    }

    @SuppressLint("MissingPermission")
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        locationApi.getCurrentLocationSettings();


        return START_NOT_STICKY;
    }


    private void stopLocationUpdates() {

        locationApi.stopLocationUpdates();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            stopForeground(true); //true will remove notification
        }
        stopLocationUpdates();
    }

    public void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            CharSequence name = "Channel name";
            String description = "Description";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("NOTIFICATION_CHANNEL", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getApplicationContext().getSystemService(NotificationManager.class);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }
    }

    private static final String TAG = "LocationService";

    private static int count = 0;

    @Override
    public void onLocationSuccess(Location location) {

        updateUserLocationIntoFirebaseDatabase(location);

//        count++;
//        Log.e(TAG, "onLocationSuccess: " + count);
        showNotification("Your current location is updating " + location.getLatitude() + "  " + location.getLongitude());
    }

    @Override
    public void onLocationFail(Exception e) {


        if (e instanceof ResolvableApiException) {

            Toast.makeText(this, "Please enable location in your phone settings ", Toast.LENGTH_SHORT).show();
            onDestroy();
        } else {

            Toast.makeText(this, "" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private Random random = new Random();

    private void updateUserLocationIntoFirebaseDatabase(Location location) {


        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        String key = database.getReference().push().getKey();
        DatabaseReference myRef = database.getReference("users");

        String name = pref.getName();
        String number = pref.getMobileNo();

        FirebaseUserLoc firebaseUserLoc = new FirebaseUserLoc();

        firebaseUserLoc.setName(name);
        firebaseUserLoc.setNumber(number);

        FirebaseUserLoc.Location location1 = new FirebaseUserLoc.Location();
        location1.setLat(location.getLatitude());
        location1.setLng(location.getLongitude());
        firebaseUserLoc.setLocation(location1);

        myRef.child(number).setValue(firebaseUserLoc);


        for (int i = 0; i < 100; i++) {

//            int n = random.nextInt(5);

            name = name + " " + i;
            number = number + " " + i;

            firebaseUserLoc = new FirebaseUserLoc();

            firebaseUserLoc.setName(name);
            firebaseUserLoc.setNumber(number);

            location1 = new FirebaseUserLoc.Location();
            location1.setLat(location.getLatitude() + i);
            location1.setLng(location.getLongitude() + i);
            firebaseUserLoc.setLocation(location1);

            myRef.child(number).setValue(firebaseUserLoc);

        }

    }


}
