package com.example.dailye;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.service.notification.StatusBarNotification;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class GPSListener implements LocationListener {

    Context context;
    private static final String TAG = "MainActivity";
    MapSettingFragment mapSettingFragment;
    String channelId = "channel";
    String channelName = "Channel Name";
    NotificationManager notificationManager;
    LatLng studyLocation;
    Notification.Builder builder;
    static MyNotificationListenerService myNotificationListenerService = new MyNotificationListenerService();
    public GPSListener() {

    }

    public GPSListener(LatLng location, Context context) {
        this.studyLocation = location;
        this.context = context;
    }

    @Override
    public void onLocationChanged(Location location) {

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        mapSettingFragment = new MapSettingFragment();

        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();

        Log.d(TAG, "pointB latitude : " + latitude + ", pointB longitude : " + longitude);

        Location myStudyLocation = new Location("point A");
        myStudyLocation.setLatitude(studyLocation.latitude);
        myStudyLocation.setLongitude(studyLocation.longitude);

        Log.d(TAG, "pointA latitude : " + studyLocation.latitude + ", pointA longitude : " + studyLocation.longitude);

        Location curLocation = new Location("point B");
        curLocation.setLatitude(latitude);
        curLocation.setLongitude(longitude);

        int distance = (int) curLocation.distanceTo(myStudyLocation);
        Log.d(TAG, "두 지역의 거리 차 : " + distance);
        if (distance <= 100) {
            Log.d(TAG, "집중 모드 서비스 Start ");
            makeNotification(context);
            myNotificationListenerService.clearNotification();
        } else {
            Log.d(TAG, "집중 모드 서비스 Stop ");
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    private void makeNotification(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context,
                1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification n = null;

        if ( android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = new NotificationChannel(channelId, channelName, importance);
            mChannel.setDescription("Channel Description");
            mChannel.enableLights(true);
            mChannel.setLightColor(Color.GREEN);
            mChannel.enableVibration(true);
            mChannel.setVibrationPattern(new long[]{100, 200, 100, 200});
            mChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
            notificationManager.createNotificationChannel(mChannel);

            builder = new Notification.Builder(context, channelId)
                    .setContentTitle("알림")
                    .setContentText("나의 공부 지역에 진입했습니다. 오늘의 공부를 시작하세요!")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_lightbulb)
                    .setAutoCancel(true);
            n = builder.build();
        } else {
            Toast.makeText(context, "This device's OS is less than Oreo", Toast.LENGTH_SHORT).show();
        }

        n.flags |= Notification.FLAG_NO_CLEAR | Notification.FLAG_ONGOING_EVENT;

        notificationManager.notify(1, n);
    }
}
