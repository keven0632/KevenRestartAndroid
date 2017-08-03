package com.cihon.androidrestart_keven.activity;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.SwipeDismissBehavior;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.cihon.androidrestart_keven.R;

public class SwipeDismissBehaviorActivity extends AppCompatActivity {

    private SwipeDismissBehavior swipeDismissBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_dismiss_behavior);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        swipeDismissBehavior = new SwipeDismissBehavior();
        swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_ANY);
        swipeDismissBehavior.setListener(onDismissListener);
        CoordinatorLayout.LayoutParams coordinatorParams = (CoordinatorLayout.LayoutParams) findViewById(R.id.textview).getLayoutParams();
        coordinatorParams.setBehavior(swipeDismissBehavior);

        swipeDismissBehavior.setDragDismissDistance(0.5F);
        swipeDismissBehavior.setStartAlphaSwipeDistance(0F);
        swipeDismissBehavior.setEndAlphaSwipeDistance(0.5F);
        swipeDismissBehavior.setSensitivity(0);
        swipeDismissBehavior.setSwipeDirection(SwipeDismissBehavior.SWIPE_DIRECTION_START_TO_END);
    }

    private SwipeDismissBehavior.OnDismissListener onDismissListener = new SwipeDismissBehavior.OnDismissListener() {
        @Override
        public void onDismiss(View view) {
        }

        @Override
        public void onDragStateChanged(int state) {
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}
