package com.cihon.androidrestart_keven;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cihon.androidrestart_keven.camera.CameraActivity;




public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBt_recyleView;
    private Button mBt_fragment;
    private Button bt_camrea;
    public final static int REQUEST_CAMERA = 1;
    private Button bt_webview;
    private Button bt_carnum;
    private Button bt_auto;
    private Button bt_srcoll;
    private Button bt_srcoll_title;

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

        bt_srcoll_title =(Button) findViewById(R.id.bt_srcoll_title);
        bt_srcoll_title.setOnClickListener(this);



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
            case R.id.bt_camrea:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA);
                    if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//                if(!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)) {
//                    Toast.makeText(context,"拍照和SD卡权限已被禁止，请手动开启",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, REQUEST_CAMERA);
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
