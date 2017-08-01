package com.cihon.androidrestart_keven.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cihon.androidrestart_keven.R;

/**
 * Created by zhengjian on 2017/7/26.
 */

public class PassWordView extends RelativeLayout {

    private View mView;
    private EditText mEt_password;
    private TextView mTv1;
    private TextView mTv2;
    private TextView mTv3;
    private TextView mTv4;
    private TextView mTv5;
    private TextView mTv6;

    public  void clear(){
        mEt_password.setText("");
//        mTv1.setText("");
//        mTv2.setText("");
//        mTv3.setText("");
//        mTv4.setText("");
//        mTv5.setText("");
//        mTv6.setText("");
    }

    public PassWordView(Context context) {
        super(context);
        initView(context);
    }

    public PassWordView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public PassWordView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    private void initView(Context context) {
        mView = View.inflate(context, R.layout.layout_password, this);
        mEt_password = (EditText) mView.findViewById(R.id.et_password);
        mTv1 = (TextView) mView.findViewById(R.id.tv1);
        mTv2 = (TextView) mView.findViewById(R.id.tv2);
        mTv3 = (TextView) mView.findViewById(R.id.tv3);
        mTv4 = (TextView) mView.findViewById(R.id.tv4);
        mTv5 = (TextView) mView.findViewById(R.id.tv5);
        mTv6 = (TextView) mView.findViewById(R.id.tv6);

        mEt_password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()==1){
                    mTv1.setText(s.toString());
                    mTv2.setText("");
                    mTv3.setText("");
                    mTv4.setText("");
                    mTv5.setText("");
                    mTv6.setText("");
                }else if(s.length()==2){
                    mTv1.setText(s.toString().substring(0,1));
                    mTv2.setText(s.toString().substring(1,2));
                    mTv3.setText("");
                    mTv4.setText("");
                    mTv5.setText("");
                    mTv6.setText("");
                }else if(s.length()==3){
                    mTv1.setText(s.toString().substring(0,1));
                    mTv2.setText(s.toString().substring(1,2));
                    mTv3.setText(s.toString().substring(2,3));
                    mTv4.setText("");
                    mTv5.setText("");
                    mTv6.setText("");
                }else if(s.length()==4){
                    mTv1.setText(s.toString().substring(0,1));
                    mTv2.setText(s.toString().substring(1,2));
                    mTv3.setText(s.toString().substring(2,3));
                    mTv4.setText(s.toString().substring(3,4));
                    mTv5.setText("");
                    mTv6.setText("");
                }else if(s.length()==5){
                    mTv1.setText(s.toString().substring(0,1));
                    mTv2.setText(s.toString().substring(1,2));
                    mTv3.setText(s.toString().substring(2,3));
                    mTv4.setText(s.toString().substring(3,4));
                    mTv5.setText(s.toString().substring(4,5));
                    mTv6.setText("");
                }else if(s.length()==6){
                    mTv1.setText(s.toString().substring(0,1));
                    mTv2.setText(s.toString().substring(1,2));
                    mTv3.setText(s.toString().substring(2,3));
                    mTv4.setText(s.toString().substring(3,4));
                    mTv5.setText(s.toString().substring(4,5));
                    mTv6.setText(s.toString().substring(5,6));
                }else if(s.length()==0){
                    mTv1.setText("");
                    mTv2.setText("");
                    mTv3.setText("");
                    mTv4.setText("");
                    mTv5.setText("");
                    mTv6.setText("");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}
