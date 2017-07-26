package com.cihon.androidrestart_keven.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.cihon.androidrestart_keven.R;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpActivity extends AppCompatActivity {

    private OkHttpClient mClient;
    private TextView mTv_response;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ok_http);

        mTv_response = (TextView) findViewById(R.id.tv_response);
        /**
         * 使用okHttp进行get请求
         */
        new Thread(() -> okHttpGet()).start();
/**
 * 使用okHttp进行post请求
 */
//        new Thread(() -> okHttpPost()).start();

    }

    private void okHttpGet() {
        mClient = new OkHttpClient();
        Request request = new Request.Builder().url("http://www.baidu.com").build();
        try {
            Response response = mClient.newCall(request).execute();
            String responseData = response.body().string();
            runOnUiThread(() -> mTv_response.setText(responseData));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * okHttp进行post请求
     */
    private void okHttpPost() {
        RequestBody requestBody = new FormBody.Builder().add("username", "admin").add("password", "123456").build();
        Request request = new Request.Builder().url("http://www.baidu.com").post(requestBody).build();
        Response response = null;
        try {
            response = mClient.newCall(request).execute();
            String responseData = response.body().string();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
