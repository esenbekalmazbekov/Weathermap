package com.example.weathermap;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

@SuppressLint("Registered")
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        if(remoteMessage.getData().isEmpty())
            showNotification(Objects.requireNonNull(remoteMessage.getNotification()).getTitle(),remoteMessage.getNotification().getBody());
        else
            showNotification(remoteMessage.getData());
    }

    private void showNotification(Map<String, String> data) {
        String title = data.get("title");
        String body = data.get("body");

        showNotification(title,body);
    }

    private void showNotification(String title, String body) {
        NotificationManager notificationManager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "com.example.realtimedatabase";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,"Notification",
                    NotificationManager.IMPORTANCE_DEFAULT);

            notificationChannel.setDescription("EDMT Channel");
            notificationChannel.enableLights(true);
            notificationChannel.setLightColor(Color.BLUE);
            notificationChannel.setVibrationPattern(new long[]{0,1000,500,1000});
            notificationChannel.enableLights(true);
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(notificationChannel);
            }
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this,NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.ic_action_name)
                .setContentTitle(title)
                .setContentText(body)
                .setColor(Color.RED)
                .setContentInfo("Info");

        if (notificationManager != null) {
            notificationManager.notify(new Random().nextInt(),notificationBuilder.build());
        }
    }


    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        Log.d("TokenFirebase", s);
    }
}
