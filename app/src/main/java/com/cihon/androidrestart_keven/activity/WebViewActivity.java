package com.cihon.androidrestart_keven.activity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.camera.CameraActivity;
import com.cihon.androidrestart_keven.camera.CameraSurfaceView;

import static com.cihon.androidrestart_keven.util.Constant.REQUEST_CAMERA;

public class WebViewActivity extends AppCompatActivity implements CameraSurfaceView.TransferPhoto {

    private WebView mWebView;
    String s = "";
    public static WebViewActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        instance = this;
//        Button bt = (Button) findViewById(R.id.bt);
//
//        bt.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String url = "javascript:" + "getWord('" + s + "')";
//                mWebView.loadUrl(url);
//            }
//        });

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
                int permissionCheck = ContextCompat.checkSelfPermission(WebViewActivity.this, Manifest.permission.CAMERA);
                if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
//                if(!ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.CAMERA)) {
//                    Toast.makeText(context,"拍照和SD卡权限已被禁止，请手动开启",Toast.LENGTH_SHORT).show();
//                    return;
//                }
                    ActivityCompat.requestPermissions(WebViewActivity.this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS}, REQUEST_CAMERA);
                    return;
                }
                startActivity(new Intent(WebViewActivity.this, CameraActivity.class));
            } else {
                startActivity(new Intent(WebViewActivity.this, CameraActivity.class));
            }
        }

    }
}
