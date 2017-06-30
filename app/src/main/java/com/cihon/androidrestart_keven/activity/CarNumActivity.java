package com.cihon.androidrestart_keven.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;

import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.camera.CameraActivity;
import com.cihon.androidrestart_keven.camera.CameraSurfaceView;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.cihon.androidrestart_keven.util.Constant.REQUEST_CAMERA;


public class CarNumActivity extends AppCompatActivity implements View.OnClickListener , CameraSurfaceView.TransferPhoto{
    String Province = " 京  浙  津  皖  沪  闽  渝  赣  港  鲁  澳  豫  鄂  新   湘  宁  粤  藏  桂  川 冀  贵 晋 云  辽  陕 吉  甘 黑  青   苏   ";
    String[] pro = {"京", "浙", "津", "皖", "沪", "闽", "渝", "赣", "港", "鲁", "澳", "豫", "鄂", "新", "湘", "宁", "粤", "藏", "桂", "川", "冀", "贵", "晋", "云", "辽", "陕", "吉", "甘", "黑", "青", "苏"};
    String[] n_second = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q"};
    String[] abc = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    String[] abc123 = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    String[] a123 = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};


    List<String> list_abc = new ArrayList<>();
    private WebView mWebView;


    String s = "";
    public static CarNumActivity instance = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_car_num);
        for (int i = 0; i < abc.length; i++) {
            list_abc.add(abc[i]);
        }

        Button bt = (Button) findViewById(R.id.button);
        bt.setOnClickListener(this);


//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                StringBuilder sb = new StringBuilder();
//                for (int i = 0; i < 3000; i++) {
//
//                    int index = (int) (Math.random() * pro.length);
////                    String s = pro[index];
//                    String s = "吉";
//                    sb.append(s);
//
//                    int index2 = (int) (Math.random() * n_second.length);
//                    String s2 = n_second[index2];
//                    sb.append(s2);
//
//                    int index3 = (int) (Math.random() * a123.length);
//                    String s3 = a123[index3];
//                    sb.append(s3);
//
//                    String s4="";
//                    if(list_abc.contains(s3)){
//                        int index4 = (int) (Math.random() * a123.length);
//                         s4 = a123[index4];
//                        sb.append(s4);
//
//                        int index5 = (int) (Math.random() * a123.length);
//                        String s5 = a123[index5];
//                        sb.append(s5);
//
//                        int index6 = (int) (Math.random() * a123.length);
//                        String s6 = a123[index6];
//                        sb.append(s6);
//
//                        int index7 = (int) (Math.random() * a123.length);
//                        String s7 = a123[index7];
//                        sb.append(s7);
//                    }else {
//                        int index4 = (int) (Math.random() * abc.length);
//                        s4 = abc[index4];
//                        sb.append(s4);
//
//                        int index5 = (int) (Math.random() * a123.length);
//                        String s5 = a123[index5];
//                        sb.append(s5);
//
//                        int index6 = (int) (Math.random() * a123.length);
//                        String s6 = a123[index6];
//                        sb.append(s6);
//
//                        int index7 = (int) (Math.random() * a123.length);
//                        String s7 = a123[index7];
//                        sb.append(s7);
//
//                    }
//
//
//
////
////                    int index5 = (int) (Math.random() * abc.length);
////                    String s5 = abc[index5];
////                    sb.append(s5);
////
////                    int index6 = (int) (Math.random() * abc.length);
////                    String s6 = abc[index6];
////                    sb.append(s6);
////
////                    int index7 = (int) (Math.random() * abc.length);
////                    String s7 = abc[index7];
////                    sb.append(s7);
//
//                    sb.append("\n");
//
//
//                }
//                Log.e("Log", sb.toString());
//
//            }
//        });

        instance = this;
        mWebView = (WebView) findViewById(R.id.webview);
        initWebView();
    }

    /**
     * 初始化webview
     */
    public void initWebView() {
        MyWebViewClient webViewClient = new MyWebViewClient();
        mWebView.setWebViewClient(webViewClient);
        WebSettings webSettings = mWebView.getSettings();

        //打开页面时，自适应屏幕
        webSettings.setLoadWithOverviewMode(true);
        //将图片调整到适合webview的大小
        webSettings.setUseWideViewPort(true);
        //页面支持缩放
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setJavaScriptEnabled(true);

//        mWebView.loadUrl("http://webservice.cihon.cn/onStarActive1/reupload.html");
        mWebView.loadUrl("http://123.57.60.91/onStar3/vehicle-License.html");
//        mWebView.loadUrl("http://wxpay.wxutil.com/mch/pay/h5.v2.php");

//        在js中调用本地java方法
        mWebView.addJavascriptInterface(new JsInterface(this), "demo");
    }

    /**
     * @param str 传递给h5页面数据
     */
    @Override
    public void imgBase64(String str, Bitmap b) {

        Log.e("Log", "webview是空的--" + (mWebView == null));
        Log.e("Log", "传递给webview的数据是--" + str);
        String url = "javascript:" + "getWord('" + str + "')";
        mWebView.loadUrl(url);

        ImageView iv = (ImageView) findViewById(R.id.img);
        iv.setImageBitmap(b);


    }

    @Override
    public void finish() {
        ViewGroup view = (ViewGroup) getWindow().getDecorView();
        view.removeAllViews();
        super.finish();
    }

    private class JsInterface {
        private Context mContext;

        public JsInterface(Context context) {
            this.mContext = context;
        }

        //在js中调用window.demo.takePhoto()，便会触发此方法。
        //拍照
        @JavascriptInterface
        public void takePhoto() {
            Log.e("Log", "h5进行调用");
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                int permissionCheck = ContextCompat.checkSelfPermission(CarNumActivity.this, Manifest.permission.CAMERA);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//                if(!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)) {
//                    Toast.makeText(context,"拍照和SD卡权限已被禁止，请手动开启",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                    ActivityCompat.requestPermissions(CarNumActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, REQUEST_CAMERA);
                    return;
                }
                startActivity(new Intent(CarNumActivity.this, CameraActivity.class));
            } else {
                startActivity(new Intent(CarNumActivity.this, CameraActivity.class));
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button:
                initNum();

                break;
        }
    }

    /**
     * 交强12xxx05072016xxxxxx???商业12xxx05282016xxxxxx
     */
    private void initNum() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 10000; i++) {
            sb.append("交强12");

            int index = (int) (Math.random() * a123.length);
            String s1 = a123[index];
            sb.append(s1);

            int index2 = (int) (Math.random() * a123.length);
            String s2 = a123[index2];
            sb.append(s2);

            int index3 = (int) (Math.random() * a123.length);
            String s3 = a123[index3];
            sb.append(s3);

            sb.append("05072016");

            int index4 = (int) (Math.random() * a123.length);
            String s4 = a123[index4];
            sb.append(s4);

            int index5 = (int) (Math.random() * a123.length);
            String s5 = a123[index5];
            sb.append(s5);

            int index6 = (int) (Math.random() * a123.length);
            String s6 = a123[index6];
            sb.append(s6);

            int index7 = (int) (Math.random() * a123.length);
            String s7 = a123[index7];
            sb.append(s7);

            int index8 = (int) (Math.random() * a123.length);
            String s8 = a123[index8];
            sb.append(s8);

            int index9 = (int) (Math.random() * a123.length);
            String s9 = a123[index9];
            sb.append(s9);

            sb.append("???商业12");
            int index11 = (int) (Math.random() * a123.length);
            int index12 = (int) (Math.random() * a123.length);
            int index13 = (int) (Math.random() * a123.length);
            int index14 = (int) (Math.random() * a123.length);
            int index15 = (int) (Math.random() * a123.length);
            int index16 = (int) (Math.random() * a123.length);
            int index17 = (int) (Math.random() * a123.length);
            int index18 = (int) (Math.random() * a123.length);
            int index19 = (int) (Math.random() * a123.length);
            String s11 = a123[index11];
            String s12 = a123[index12];
            String s13 = a123[index13];
            String s14 = a123[index14];
            String s15 = a123[index15];
            String s16 = a123[index16];
            String s17 = a123[index17];
            String s18 = a123[index18];
            String s19 = a123[index19];
            sb.append(s11);
            sb.append(s12);
            sb.append(s13);
            sb.append("05282016");
            sb.append(s14);
            sb.append(s15);
            sb.append(s16);
            sb.append(s17);
            sb.append(s18);
            sb.append(s19);
            sb.append("\n");
        }


        Log.e("Tag", sb.toString());
        String path = takePicRootDir(this)+"CarNum.txt";


        try {
            File file=new File(path);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.flush();
            fileWriter.write(sb.toString());
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 判断当前存储卡是否可用
     **/
    public boolean checkSDCardAvailable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取当前需要查询的文件夹
     **/
    public String takePicRootDir(Context context) {
        if (checkSDCardAvailable()) {
            return Environment.getExternalStorageDirectory() + File.separator;
        } else {
            return context.getFilesDir().getAbsolutePath() + File.separator;
        }
    }
}
