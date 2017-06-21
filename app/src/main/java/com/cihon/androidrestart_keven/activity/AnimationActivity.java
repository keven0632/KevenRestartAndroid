package com.cihon.androidrestart_keven.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.cihon.androidrestart_keven.R;

public class AnimationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Òþ²Ø±êÌâÀ¸
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //Òþ²Ø×´Ì¬À¸
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_animation);

    }
}
