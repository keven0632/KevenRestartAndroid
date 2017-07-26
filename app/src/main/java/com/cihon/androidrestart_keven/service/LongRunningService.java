package com.cihon.androidrestart_keven.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.SystemClock;

public class LongRunningService extends Service {
    public LongRunningService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        int s10 = 10 * 1000;//这是十秒时间
        long triggerAtTime = SystemClock.elapsedRealtime() + s10;
        Intent i=new Intent(this,LongRunningService.class);
        PendingIntent pendingIntent=PendingIntent.getService(this,0,i,0);

        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, triggerAtTime, pendingIntent);


        return super.onStartCommand(intent, flags, startId);
    }
}
