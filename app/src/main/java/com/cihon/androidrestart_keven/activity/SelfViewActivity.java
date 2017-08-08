package com.cihon.androidrestart_keven.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.cihon.androidrestart_keven.R;


public class SelfViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_self_view);

        findViewById(R.id.bt_view_linearlayout).setOnClickListener(v -> startActivity(new Intent(SelfViewActivity.this, LinearLayoutViewActivity.class)));
        findViewById(R.id.bt_view_password).setOnClickListener(v -> startActivity(new Intent(this, EditTextActivity.class)));
        findViewById(R.id.bt_view_horizontal).setOnClickListener(v -> startActivity(new Intent(this, HorizontalViewActivity.class)));

    }
}
