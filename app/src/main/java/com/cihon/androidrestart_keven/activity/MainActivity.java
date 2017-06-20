package com.cihon.androidrestart_keven.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.camera.CameraActivity;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBt_recyleView;
    private Button mBt_fragment;
    private Button bt_camrea;
    public final static int REQUEST_CAMERA = 1;
    public final static int REQUEST_STORAGE = 2;
    public final static int REQUEST_LOCATION = 3;
    private Button bt_webview;
    private Button bt_carnum;
    private Button bt_auto;
    private Button bt_srcoll;
    private Button bt_srcoll_title;
    private Button bt_download;
    private Button bt_popupWindow;
    private Context context;
    private Button bt_location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mBt_recyleView = (Button) findViewById(R.id.bt_recyleview);
        mBt_recyleView.setOnClickListener(this);
        mBt_fragment = (Button) findViewById(R.id.bt_fragment);
        mBt_fragment.setOnClickListener(this);
        bt_camrea = (Button) findViewById(R.id.bt_camrea);
        bt_camrea.setOnClickListener(this);

        bt_webview = (Button) findViewById(R.id.bt_webview);
        bt_webview.setOnClickListener(this);

        bt_carnum = (Button) findViewById(R.id.bt_carnum);
        bt_carnum.setOnClickListener(this);

        bt_auto = (Button) findViewById(R.id.bt_auto);
        bt_auto.setOnClickListener(this);

        bt_srcoll = (Button) findViewById(R.id.bt_srcoll);
        bt_srcoll.setOnClickListener(this);

        bt_srcoll_title = (Button) findViewById(R.id.bt_srcoll_title);
        bt_srcoll_title.setOnClickListener(this);

        bt_download = (Button) findViewById(R.id.bt_download);
        bt_download.setOnClickListener(this);

        bt_popupWindow = (Button) findViewById(R.id.bt_popupWindow);
        bt_popupWindow.setOnClickListener(this);

        bt_location = (Button) findViewById(R.id.bt_location);
        bt_location.setOnClickListener(this);

        context = this;


        getPermission();

    }

    /**
     * 获取相应权限
     */
    private void getPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int permissionCheck = ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA);
            int permissionCheck2 = ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
            int permissionCheck3 = ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION);
            if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.ACCESS_COARSE_LOCATION}, MainActivity.REQUEST_CAMERA);
                return;
            }
//            if (permissionCheck2 != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, MainActivity.REQUEST_STORAGE);
//                return;
//            }
//            if (permissionCheck3 != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, MainActivity.REQUEST_LOCATION);
//                return;
//            }

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        switch (requestCode) {
            case MainActivity.REQUEST_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    Intent intent = new Intent(context, MipcaActivityCapture.class);
//                    startActivityForResult(intent, 1);
                }
                break;
        }

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_recyleview:
                startActivity(new Intent(this, RecyleViewActivity.class));
                break;
            case R.id.bt_fragment:
                startActivity(new Intent(this, FragmentActivity.class));
                break;
            case R.id.bt_webview:
                startActivity(new Intent(this, WebViewActivity.class));
                break;
            case R.id.bt_srcoll:
                startActivity(new Intent(this, TabLayoutActivity.class));
                break;
            case R.id.bt_carnum:
                startActivity(new Intent(this, CarNumActivity.class));
                break;
            case R.id.bt_auto:
                startActivity(new Intent(this, AutoImgActivity.class));
                break;
            case R.id.bt_srcoll_title:
                startActivity(new Intent(this, ScrollingActivity.class));
                break;
            case R.id.bt_download:
                startActivity(new Intent(this, DownLoadActivity.class));
                break;
            case R.id.bt_popupWindow:
                startActivity(new Intent(this, PopupWindowActivity.class));
                break;
            case R.id.bt_location:
                startActivity(new Intent(this, BaiduLocation.class));
                break;
            case R.id.bt_camrea:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//                if(!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)) {
//                    Toast.makeText(context,"拍照和SD卡权限已被禁止，请手动开启",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, REQUEST_CAMERA);
                        return;
                    }
                    startActivity(new Intent(this, CameraActivity.class));
                } else {
                    startActivity(new Intent(this, CameraActivity.class));
                }


                break;

        }
    }
}
