package com.cihon.androidrestart_keven.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPropertyAnimatorListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.adapter.ListRecyclerAdapter;
import com.cihon.androidrestart_keven.util.AnimatorUtil;

import java.util.ArrayList;
import java.util.List;




public class BackTopActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private FloatingActionButton FAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_top);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            list.add("我是第" + i + "个");
        }
        linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setSmoothScrollbarEnabled(true);
        recyclerView.setLayoutManager(linearLayoutManager);
        ListRecyclerAdapter adapter = new ListRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

        FAB = (FloatingActionButton) findViewById(R.id.fab);
        FAB.setOnClickListener(v -> {
            linearLayoutManager.scrollToPosition(0);
            hideFAB();
        });
    }

//    private boolean isInitializeFAB = false;
//
//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//        if (!isInitializeFAB) {
//            isInitializeFAB = true;
//            hideFAB();
//        }
//    }

    private void hideFAB() {
        FAB.postDelayed(() -> AnimatorUtil.scaleHide(FAB, new ViewPropertyAnimatorListener() {
            @Override
            public void onAnimationStart(View view) {
            }

            @Override
            public void onAnimationEnd(View view) {
                FAB.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationCancel(View view) {
            }
        }), 500);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
