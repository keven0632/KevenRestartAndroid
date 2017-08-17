package com.cihon.androidrestart_keven.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.camera.CameraActivity;

import static com.cihon.androidrestart_keven.util.Constant.REQUEST_CAMERA;

public class TakePictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picyure);

        findViewById(R.id.bt_camrea1).setOnClickListener(v -> startActivity(new Intent(TakePictureActivity.this, TakePhotoActivity.class)));
        findViewById(R.id.bt_webview).setOnClickListener(v -> startActivity(new Intent(TakePictureActivity.this, WebViewActivity.class)));
        findViewById(R.id.bt_camrea).setOnClickListener(v -> {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permissionCheck = ContextCompat.checkSelfPermission(TakePictureActivity.this, Manifest.permission.CAMERA);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(TakePictureActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, REQUEST_CAMERA);
                    return;
                }
                startActivity(new Intent(TakePictureActivity.this, CameraActivity.class));
            } else {
                startActivity(new Intent(TakePictureActivity.this, CameraActivity.class));
            }
        });
    }
}
