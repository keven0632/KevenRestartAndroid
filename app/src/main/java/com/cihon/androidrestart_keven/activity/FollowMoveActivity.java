package com.cihon.androidrestart_keven.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.cihon.androidrestart_keven.R;

public class FollowMoveActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_follow_move);

        findViewById(R.id.btn_move).setOnTouchListener((v, event) -> {
            if (event.getAction() == MotionEvent.ACTION_MOVE) {
                v.setX(event.getRawX() - v.getWidth() / 2);
                v.setY(event.getRawY() - v.getHeight() / 2);
            }
            return true;
        });
    }
}
