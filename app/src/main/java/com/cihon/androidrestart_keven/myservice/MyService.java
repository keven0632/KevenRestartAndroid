package com.cihon.androidrestart_keven.myservice;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.activity.MainActivity;
import com.cihon.androidrestart_keven.util.LogUtil;


public class MyService extends Service {

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        LogUtil.e("onCreate");

        /**
         * 使用前台服务
         */
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification notification = new NotificationCompat.Builder(this)
                .setContentTitle("title")
                .setContentText("text")
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            //处理具体的逻辑，耗时操作
            stopSelf();
        }).start();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private DownloadBinder mBinder = new DownloadBinder();

    class DownloadBinder extends Binder {

        public void startDownload() {
            Log.e("Log", "startDownload");
        }

        public int getProgress() {
            LogUtil.e("getProgress");
            return 0;
        }

    }


    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
