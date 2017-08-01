package com.cihon.androidrestart_keven.activity;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.view.PassWordView;

public class EditTextActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_text);


//        PassWordView view= (PassWordView) findViewById(R.id.ps);
//        Button bt_cllear= (Button) findViewById(R.id.bt_cllear);
//        bt_cllear.setOnClickListener(v -> view.clear());

        AlertDialog alertDialog = new AlertDialog.Builder(EditTextActivity.this).create();
        alertDialog.show();
        alertDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        Window window = alertDialog.getWindow();
//        View view_layout=getLayoutInflater().inflate(R.layout.activity_edit_text,null);
        window.setContentView(R.layout.activity_edit_text);
        PassWordView view1 = (PassWordView) window.findViewById(R.id.ps);
        Button bt_cllear1 = (Button) window.findViewById(R.id.bt_cllear);
        bt_cllear1.setOnClickListener(v -> view1.clear());


//        EditText et_password = (EditText) findViewById(R.id.et_password);
//        TextView tv1 = (TextView) findViewById(R.id.tv1);
//        TextView tv2 = (TextView) findViewById(R.id.tv2);
//        TextView tv3 = (TextView) findViewById(R.id.tv3);
//        TextView tv4 = (TextView) findViewById(R.id.tv4);
//        TextView tv5 = (TextView) findViewById(R.id.tv5);
//        TextView tv6 = (TextView) findViewById(R.id.tv6);
//
//        et_password.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if(s.length()==1){
//                    tv1.setText(s.toString());
//                    tv2.setText("");
//                    tv3.setText("");
//                    tv4.setText("");
//                    tv5.setText("");
//                    tv6.setText("");
//                }else if(s.length()==2){
//                    tv1.setText(s.toString().substring(0,1));
//                    tv2.setText(s.toString().substring(1,2));
//                    tv3.setText("");
//                    tv4.setText("");
//                    tv5.setText("");
//                    tv6.setText("");
//                }else if(s.length()==3){
//                    tv1.setText(s.toString().substring(0,1));
//                    tv2.setText(s.toString().substring(1,2));
//                    tv3.setText(s.toString().substring(2,3));
//                    tv4.setText("");
//                    tv5.setText("");
//                    tv6.setText("");
//                }else if(s.length()==4){
//                    tv1.setText(s.toString().substring(0,1));
//                    tv2.setText(s.toString().substring(1,2));
//                    tv3.setText(s.toString().substring(2,3));
//                    tv4.setText(s.toString().substring(3,4));
//                    tv5.setText("");
//                    tv6.setText("");
//                }else if(s.length()==5){
//                    tv1.setText(s.toString().substring(0,1));
//                    tv2.setText(s.toString().substring(1,2));
//                    tv3.setText(s.toString().substring(2,3));
//                    tv4.setText(s.toString().substring(3,4));
//                    tv5.setText(s.toString().substring(4,5));
//                    tv6.setText("");
//                }else if(s.length()==6){
//                    tv1.setText(s.toString().substring(0,1));
//                    tv2.setText(s.toString().substring(1,2));
//                    tv3.setText(s.toString().substring(2,3));
//                    tv4.setText(s.toString().substring(3,4));
//                    tv5.setText(s.toString().substring(4,5));
//                    tv6.setText(s.toString().substring(5,6));
//                }else if(s.length()==0){
//                    tv1.setText("");
//                    tv2.setText("");
//                    tv3.setText("");
//                    tv4.setText("");
//                    tv5.setText("");
//                    tv6.setText("");
//                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });


    }
}
