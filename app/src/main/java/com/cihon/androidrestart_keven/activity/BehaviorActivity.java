package com.cihon.androidrestart_keven.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.cihon.androidrestart_keven.R;

public class BehaviorActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_behavior);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btn_back_top).setOnClickListener(this);
        findViewById(R.id.btn_zhihu).setOnClickListener(this);
        findViewById(R.id.btn_bottom_sheet).setOnClickListener(this);
        findViewById(R.id.btn_swipe_dismiss).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_back_top) {// 回到顶部按钮动画。
            startActivity(new Intent(this, BackTopActivity.class));
        } else if (v.getId() == R.id.btn_zhihu) {// 仿知乎首页隐藏按钮动画。
            startActivity(new Intent(this, ZhihuActivity.class));
        } else if (v.getId() == R.id.btn_bottom_sheet) {// 底部覆盖。
            startActivity(new Intent(this, BottomSheetBehaviorActivity.class));
        } else if (v.getId() == R.id.btn_swipe_dismiss) {// 滑动删除。
            startActivity(new Intent(this, SwipeDismissBehaviorActivity.class));
        }
    }
}
