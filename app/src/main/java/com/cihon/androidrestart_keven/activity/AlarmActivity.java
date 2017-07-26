package com.cihon.androidrestart_keven.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.service.LongRunningService;

public class AlarmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        Intent intent = new Intent(getApplicationContext(), LongRunningService.class);
        getApplicationContext().startService(intent);
    }
}
