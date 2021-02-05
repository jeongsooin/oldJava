package com.example.dailye;

import android.content.Intent;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class MyNotificationListenerService extends NotificationListenerService {

    public MyNotificationListenerService() {
    }

    StatusBarNotification sbn;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("NotificationListener", "[Daily_E] onCreate()");

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("NotificationListener", "[Daily_E] onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("NotificationListener", "[Daily_E] onDestroy()");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        this.sbn = sbn;
        Log.i("NotificationListener", "[Daily_E] Key:" + sbn.getKey());
        Log.i("NotificationListener", "[Daily_E] onNotificationPosted() - " + sbn.toString());
        Log.i("NotificationListener", "[Daily_E] PackageName:" + sbn.getPackageName());
        Log.i("NotificationListener", "[Daily_E] PostTime:" + sbn.getPostTime());

//        Notification notification = sbn.getNotification();
//        Bundle extras = notification.extras;
//        String title = extras.getString(Notification.EXTRA_TITLE);
//        int smallIconRes = extras.getInt(Notification.EXTRA_SMALL_ICON);
//       Bitmap largeIcon = ((Bitmap) extras.getParcelable(Notification.EXTRA_LARGE_ICON));
//        CharSequence text = extras.getCharSequence(Notification.EXTRA_TEXT);
//        CharSequence subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT);

//       Log.i("NotificationListener", "[Daily_E] Title:" + title);
//       Log.i("NotificationListener", "[Daily_E] Text:" + text);
//       Log.i("NotificationListener", "[Daily_E] Sub Text:" + subText);

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("NotificationListener", "[Daily_E] onNotificationRemoved() - " + sbn.toString());
    }

    public void clearNotification() {
        if(!sbn.getKey().equals("0|com.example.dailye|1|null|10547"))
            cancelNotification(sbn.getKey());
    }

}
