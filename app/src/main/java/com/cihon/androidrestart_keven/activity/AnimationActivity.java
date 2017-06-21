package com.cihon.androidrestart_keven.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.cihon.androidrestart_keven.R;

public class AnimationActivity extends AppCompatActivity {

    private Button mBt_allanimation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //���ر�����
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        //����״̬��
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        setContentView(R.layout.activity_animation);

        mBt_allanimation = (Button) findViewById(R.id.bt_allanimation);
        mBt_allanimation.setOnClickListener(v -> startActivity(new Intent(AnimationActivity.this, AllAnimationActivity.class)));


    }
}
