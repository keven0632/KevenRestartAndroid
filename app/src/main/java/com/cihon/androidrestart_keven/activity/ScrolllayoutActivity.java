package com.cihon.androidrestart_keven.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.listener.AppBarStateChangeListener;

public class ScrolllayoutActivity extends AppCompatActivity {

    private AppBarLayout mAppBarLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolllayout);
/**
 * ¼àÌýAppBarLayoutµÄÕÛµþ×´Ì¬
 */
        mAppBarLayout = (AppBarLayout) findViewById(R.id.app_bar);
        mAppBarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                Log.d("STATE", state.name());
                if (state == State.EXPANDED) {
                    //Õ¹¿ª×´Ì¬


                } else if (state == State.COLLAPSED) {
                    //ÕÛµþ×´Ì¬


                } else {
                    //ÖÐ¼ä×´Ì¬


                }
            }
        });
    }
}
