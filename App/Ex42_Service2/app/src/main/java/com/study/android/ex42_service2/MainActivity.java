package com.study.android.ex42_service2;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    AlarmManager am;
    Intent intent;
    PendingIntent receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

        // 예약에 의해 호출될 BroadCast Receiver 지정
        intent = new Intent(this, AlarmReceiver.class);
        receiver = PendingIntent.getBroadcast(this,
                            0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    public void onBtn1Clicked(View v) {
        // 알람시간 10초 후
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.add(Calendar.SECOND, 10);

        // RTC_WAKEUP :  인자로 넘겨진 시간을 기준으로 알람이 알람이 동작하여 PendingIntent를 전달합니다.
        // RTC : 이름에서 볼 수 있듯이 위와 똑같지만 sleep 모드에 들어간 기계를 깨우지는 않습니다.
        // ELAPSED_REALTIME_WAKEUP :  안드로이드 기계가 부팅된 시점을 기준으로 알람이 울립니다.
        // ELAPSED_REALTIME : ELAPSED_REALTIME_WAKEUP과 같지만, sleep 모드에 들어갔다면 깨우지는 않습니다
        // PendingIntent = receiver = AlarmReceiver intent
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), receiver);
    }

    public void onBtn2Clicked(View v) {
        // 60초당 한 번 알람 등록 : 24 * 60 * 60 * 1000
        // Kitkat 부터는 최소 시간이 1분이다.
        // Kitkat 사용하기 전에 원하는 것을 밀리 초 단위로 설정할 수 있지만
        // Kitkat 이후에는 최소값이 1 분 (60000 밀리 초)입니다.
        // 이것은 배터리 절약 기능이 Kitkat 버전에서 소개 되었기 때문입니다.
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime(),
                10 * 1000,  receiver);
    }

    public void onBtn3Clicked(View v) {
        am.cancel(receiver);
    }
}
