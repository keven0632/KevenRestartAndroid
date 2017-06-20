package com.cihon.androidrestart_keven.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.cihon.androidrestart_keven.PopupWindow.CommonPoupWindow;
import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.adapter.PopupAdapter;
import com.cihon.androidrestart_keven.util.CommonUtil;
import com.cihon.androidrestart_keven.util.DpUtil;
import com.cihon.androidrestart_keven.util.MyOnclickListener;

public class PopupWindowActivity extends AppCompatActivity implements CommonPoupWindow.ViewInterface {
    private CommonPoupWindow popupWindow;
    private boolean isShowRight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_popup_window);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        initToolBar(toolbar, "PopupWindow", true);
    }
    public void initToolBar(Toolbar toolbar, String name, boolean showHomeAsUp) {
        initToolBar(toolbar, name, showHomeAsUp, false);
    }

    public void initToolBar(Toolbar toolbar, String name, boolean showHomeAsUp, boolean isShowRight) {
        this.isShowRight = isShowRight;
        toolbar.setTitle(name);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(showHomeAsUp);
    }


    //���µ���
    public void showDownPop(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CommonPoupWindow.Builder(this)
                .setView(R.layout.popup_down)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimDown)
                .setViewOnclickListener(this)
                .setOutsideTouchable(true)
                .create();
        popupWindow.showAsDropDown(view);
        //�õ�button�����Ͻ�����
//        int[] positions = new int[2];
//        view.getLocationOnScreen(positions);
//        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, 0, positions[1] + view.getHeight());
    }

    //���ҵ���
    public void showRightPop(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CommonPoupWindow.Builder(this)
                .setView(R.layout.popup_left_or_right)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimHorizontal)
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAsDropDown(view, view.getWidth(), -view.getHeight());
        //�õ�button�����Ͻ�����
//        int[] positions = new int[2];
//        view.getLocationOnScreen(positions);
//        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, positions[0] + view.getWidth(), positions[1]);
    }

    //���󵯳�
    public void showLeftPop(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CommonPoupWindow.Builder(this)
                .setView(R.layout.popup_left_or_right)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimRight)
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAsDropDown(view, -popupWindow.getWidth(), -view.getHeight());
        //�õ�button�����Ͻ�����
//        int[] positions = new int[2];
//        view.getLocationOnScreen(positions);
//        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, positions[0] - popupWindow.getWidth(), positions[1]);
    }


    //ȫ������
    public void showAll(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        View upView = LayoutInflater.from(this).inflate(R.layout.popup_up, null);
        //����View�Ŀ��
        CommonUtil.measureWidthAndHeight(upView);
        popupWindow = new CommonPoupWindow.Builder(this)
                .setView(R.layout.popup_up)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, upView.getMeasuredHeight())
                .setBackGroundLevel(0.5f)//ȡֵ��Χ0.0f-1.0f ֵԽСԽ��
                .setAnimationStyle(R.style.AnimUp)
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.BOTTOM, 0, 0);
    }

    //���ϵ���
    public void showUpPop(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CommonPoupWindow.Builder(this)
                .setView(R.layout.popup_left_or_right)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setViewOnclickListener(this)
                .create();
        popupWindow.showAsDropDown(view, 0, -(popupWindow.getHeight() + view.getMeasuredHeight()));

        //�õ�button�����Ͻ�����
//        int[] positions = new int[2];
//        view.getLocationOnScreen(positions);
//        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, positions[0], positions[1] - popupWindow.getHeight());
    }


    public void showReminder(View view) {
        if (popupWindow != null && popupWindow.isShowing()) return;
        popupWindow = new CommonPoupWindow.Builder(this)
                .setView(R.layout.query_info)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .create();
        popupWindow.showAsDropDown(view, (int) (-popupWindow.getWidth() + DpUtil.dp2px(this, 20)), -(popupWindow.getHeight() + view.getMeasuredHeight()));
    }

    @Override
    public void getChildView(View view, int layoutResId) {
        //���PopupWindow�������View
        switch (layoutResId) {
            case R.layout.popup_down:
                RecyclerView recycle_view = (RecyclerView) view.findViewById(R.id.recycle_view);
                recycle_view.setLayoutManager(new GridLayoutManager(this, 3));
                PopupAdapter mAdapter = new PopupAdapter(this);
                recycle_view.setAdapter(mAdapter);
                mAdapter.setOnItemClickListener(new MyOnclickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        toast("position is " + position);
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        }
                    }
                });
                break;
            case R.layout.popup_up:
                Button btn_take_photo = (Button) view.findViewById(R.id.btn_take_photo);
                Button btn_select_photo = (Button) view.findViewById(R.id.btn_select_photo);
                Button btn_cancel = (Button) view.findViewById(R.id.btn_cancel);
                btn_take_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toast("����");
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        }
                    }
                });
                btn_select_photo.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toast("���ѡȡ");
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        }
                    }
                });
                btn_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        }
                    }
                });
                view.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        if (popupWindow != null) {
                            popupWindow.dismiss();
                        }
                        return true;
                    }
                });
                break;
            case R.layout.popup_left_or_right:
                TextView tv_like = (TextView) view.findViewById(R.id.tv_like);
                TextView tv_hate = (TextView) view.findViewById(R.id.tv_hate);
                tv_like.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toast("��һ��");
                        popupWindow.dismiss();
                    }
                });
                tv_hate.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        toast("��һ��");
                        popupWindow.dismiss();
                    }
                });
                break;
        }
    }

    /**
     * @param str ����������
     */
    public void toast(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }
}
