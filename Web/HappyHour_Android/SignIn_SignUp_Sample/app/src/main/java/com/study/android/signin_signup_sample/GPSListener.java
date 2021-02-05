package com.study.android.signin_signup_sample;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

public class GPSListener implements LocationListener {

    public Context context;
    public LatLng myLocation;
    String channelId = "channel";
    String channelName = "Channel Name";
    String resultString = "";

    public LocationManager manager;
    public static MapSettingFragment mapSettingFragment = MapSettingFragment.getInstance();
    public NotificationManager notificationManager;
    Notification.Builder builder;

    private static final LatLng SEOUL  = new LatLng(37.566535, 126.977969);

    public static GPSListener getInstance() { return new GPSListener(); }

    public GPSListener() { }

    public GPSListener(LatLng location, Context context) {
        this.myLocation = location;
        this.context = context;
        manager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public LatLng getMyLocation() {
        return myLocation;
    }

    public void setMyLocation(LatLng myLocation) {
        this.myLocation = myLocation;
    }

    public LocationManager getManager() {
        return manager;
    }

    public void setManager(LocationManager manager) {
        this.manager = manager;
    }

    public NotificationManager getNotificationManager() {
        return notificationManager;
    }

    public void setNotificationManager(NotificationManager notificationManager) {
        this.notificationManager = notificationManager;
    }

    @Override
    public void onLocationChanged(Location location) {

        mapSettingFragment = MapSettingFragment.getInstance();

        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();


        Location myStudyLocation = new Location("point A");
        myStudyLocation.setLatitude(myLocation.latitude);
        myStudyLocation.setLongitude(myLocation.longitude);

        Location curLocation = new Location("point B");
        curLocation.setLatitude(latitude);
        curLocation.setLongitude(longitude);

        int distance = (int) curLocation.distanceTo(myStudyLocation);
        if (distance <= 100) {
            makeNotification(context);
            manager.removeUpdates(this);
            Log.i("[SnowFlake] **** ", "stopLocationService() called ... ");
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderDisabled(String provider) { }

    @Override
    public void onProviderEnabled(String provider) { }

    private void makeNotification(Context context) {
        Intent intent = new Intent(context, MainActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, 1, intent, PendingIntent.FLAG_UPDATE_CURRENT);

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
                    .setOnlyAlertOnce(true)
                    .setContentTitle("알림")
                    .setContentText("식당에 도착했습니다. 맛있는 하루 되세요!")
                    .setContentIntent(pendingIntent)
                    .setSmallIcon(R.drawable.ic_lightbulb)
                    .setAutoCancel(true);
            n = builder.build();
        } else {
            Toast.makeText(context, "This device's OS is less than Oreo", Toast.LENGTH_SHORT).show();
        }

        n.flags |= Notification.FLAG_ONLY_ALERT_ONCE | Notification.FLAG_ONGOING_EVENT;

        notificationManager.notify(1, n);
    }

}
