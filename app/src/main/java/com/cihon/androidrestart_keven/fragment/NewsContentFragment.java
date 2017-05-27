package com.cihon.androidrestart_keven.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cihon.androidrestart_keven.R;

/**
 * Created by zhengjian on 2017/5/25.
 */

public class NewsContentFragment extends Fragment {

    private View mView;
    private View mVisibilityLayout;
    private TextView mNewsTitleText;
    private TextView mNewsConetntText;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.news_content_frag, container, false);
        return mView;
    }

    public void refresh(String newsTitle, String newsContent) {
        mVisibilityLayout = mView.findViewById(R.id.visibility_layout);
        mVisibilityLayout.setVisibility(View.VISIBLE);

        mNewsTitleText = (TextView) mView.findViewById(R.id.news_title);
        mNewsConetntText = (TextView) mView.findViewById(R.id.news_content);
        mNewsTitleText.setText(newsTitle);//刷新新闻标题
        mNewsConetntText.setText(newsContent);//刷新新闻内容
    }

}
