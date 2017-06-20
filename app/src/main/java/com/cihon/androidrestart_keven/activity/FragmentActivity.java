package com.cihon.androidrestart_keven.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.cihon.androidrestart_keven.R;

public class FragmentActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
//        mButton = (Button) findViewById(R.id.button);
//        mButton.setOnClickListener(this);
//        ReplaceFragment(new RightFragment());
    }

//    private void ReplaceFragment(Fragment fragment) {
//        FragmentManager manager=getSupportFragmentManager();
//        FragmentTransaction transaction=manager.beginTransaction();
//        transaction.replace(R.id.layout_right,fragment);
//        transaction.commit();
//    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.button:
//                ReplaceFragment(new AnotherRightFragment());
//                break;
        }
    }
}
