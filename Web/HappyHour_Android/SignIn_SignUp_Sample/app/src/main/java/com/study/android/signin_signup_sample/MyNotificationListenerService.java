package com.study.android.signin_signup_sample;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

public class MyNotificationListenerService extends NotificationListenerService {

    public MyNotificationListenerService() { }

    StatusBarNotification sbn;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("[SnoFlake] Listener", "[SnoFlake] onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("[SnoFlake] Listener", "[SnoFlake] onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("[SnoFlake] Listener", "[SnoFlake] onDestroy()");
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        this.sbn = sbn;

        Notification notification = sbn.getNotification();
        Bundle extras = notification.extras;
        String title = extras.getString(Notification.EXTRA_TITLE);
        CharSequence text = extras.getCharSequence(Notification.EXTRA_TEXT);
        CharSequence subText = extras.getCharSequence(Notification.EXTRA_SUB_TEXT);

        Log.i("[SnoFlake] Listener", "[SnoFlake] Title:" + title);
        Log.i("[SnoFlake] Listener", "[SnoFlake] Text:" + text);
        Log.i("[SnoFlake] Listener", "[SnoFlake] Sub Text:" + subText);

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        Log.i("[SnoFlake] Listener", "[SnoFlake] onNotificationRemoved() - " + sbn.toString());
    }
}
