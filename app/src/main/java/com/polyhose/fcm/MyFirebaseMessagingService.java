package com.polyhose.fcm;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import com.polyhose.data.pref.Pref;
import com.polyhose.data.pref.PreferencesHelper;
import com.polyhose.notification.Constants;
import com.polyhose.notification.MyNotificationManager;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import static android.support.constraint.Constraints.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {


    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);

        Log.e(TAG, "onNewToken: " + s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);


//        Log.e(TAG, "onMessageReceived: " + new Gson().toJson(remoteMessage.getNotification()));

        if (remoteMessage.getData().size() > 0) {
            //handle the data message here

            Log.e(TAG, "onMessageReceived: " + new Gson().toJson(remoteMessage.getData()));


            String title = remoteMessage.getData().get("title");
            String body = remoteMessage.getData().get("body");
            String type = remoteMessage.getData().get("type");
            String id = remoteMessage.getData().get("id");
            String status = remoteMessage.getData().get("status");

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                NotificationManager mNotificationManager =
                        (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                int importance = NotificationManager.IMPORTANCE_HIGH;
                NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
                mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
                mChannel.enableLights(true);
                mChannel.setLightColor(Color.RED);
                mChannel.enableVibration(true);
                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                mNotificationManager.createNotificationChannel(mChannel);
            }


            Pref pref = PreferencesHelper.getPreferencesInstance(this);

            pref.savePush(true);
            /*
             * Displaying a notification locally
             */
            MyNotificationManager.getInstance(this).displayNotification(title, body, type, id, status);

        }

//        //getting the title and the body
//        String title = remoteMessage.getNotification().getTitle();
//        String body = remoteMessage.getNotification().getBody();
//
//        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
//            NotificationManager mNotificationManager =
//                    (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//            int importance = NotificationManager.IMPORTANCE_HIGH;
//            NotificationChannel mChannel = new NotificationChannel(Constants.CHANNEL_ID, Constants.CHANNEL_NAME, importance);
//            mChannel.setDescription(Constants.CHANNEL_DESCRIPTION);
//            mChannel.enableLights(true);
//            mChannel.setLightColor(Color.RED);
//            mChannel.enableVibration(true);
//            mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
//            mNotificationManager.createNotificationChannel(mChannel);
//        }
//
//        /*
//         * Displaying a notification locally
//         */
//        MyNotificationManager.getInstance(this).displayNotification(title, body);


    }
}
