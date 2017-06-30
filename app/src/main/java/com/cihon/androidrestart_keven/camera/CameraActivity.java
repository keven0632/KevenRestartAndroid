package com.cihon.androidrestart_keven.camera;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;

import com.cihon.androidrestart_keven.R;

public class CameraActivity extends AppCompatActivity implements CameraSurfaceView.TransferPhoto{

    private Button button;
    private CameraSurfaceView mCameraSurfaceView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);

        mCameraSurfaceView = (CameraSurfaceView) findViewById(R.id.cameraSurfaceView);
        button = (Button) findViewById(R.id.takePic);

        button.setOnClickListener(view -> {
            mCameraSurfaceView.takePicture();
//                screenshot();

        });
    }

    /**
     * 截取屏幕
     */
//    private void screenshot() {
//        // 获取屏幕
//        View dView = getWindow().getDecorView();
//        dView.setDrawingCacheEnabled(true);
//        dView.buildDrawingCache();
//        Bitmap bmp = dView.getDrawingCache();
//        if (bmp != null) {
//            try {
////                // 获取内置SD卡路径
////                String sdCardPath = Environment.getExternalStorageDirectory().getPath();
////                // 图片文件路径
////                String filePath = sdCardPath + File.separator + "screenshot.png";
//
//                String ffPath = "/sdcard/dyk" + System.currentTimeMillis() + ".png";//照片保存路径
//                File file = new File(ffPath);
//                if (!file.exists()) {
//                    file.createNewFile();
//                }
//                FileOutputStream os = new FileOutputStream(file);
//                bmp.compress(Bitmap.CompressFormat.PNG, 100, os);
//                os.flush();
//                os.close();
//            } catch (Exception e) {
//            }
//        }
//    }




    @Override
    public void imgBase64(String str, Bitmap bitmap) {
        Log.e("Log","关闭拍照Activity");
        finish();
    }
}

