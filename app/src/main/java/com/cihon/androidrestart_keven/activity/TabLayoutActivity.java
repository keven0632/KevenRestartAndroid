package com.cihon.androidrestart_keven.activity;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cihon.androidrestart_keven.R;

import java.util.ArrayList;
import java.util.List;

public class TabLayoutActivity extends AppCompatActivity {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private List<String> mList = new ArrayList<>();
    private ArrayList<ImageView> mImageList = new ArrayList<>();
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        mTabLayout = (TabLayout) findViewById(R.id.tablayout);
        /**
         * 设置tab字体的颜色，第一个为未选中的字体的颜色，第二个为选中的字体的颜色
         */
        mTabLayout.setTabTextColors(Color.WHITE,Color.RED);
        /**
         * 设置下划线的颜色
         */
//        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimary));
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        initData();
        initToolBar();
    }

    private void initToolBar() {
        mToolbar.setNavigationIcon(R.mipmap.ic_return);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initData() {
        for (int i = 0; i < 12; i++) {
            ImageView imageView = new ImageView(TabLayoutActivity.this);
            if (i % 2 == 1) {
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.img1));

            } else {
                imageView.setImageDrawable(getResources().getDrawable(R.mipmap.img0));

            }
            mImageList.add(imageView);

        }
        if (mList.size() > 0) {
            mList.clear();
        }
        mList.add("List垂直");
        mList.add("List垂直反向");
        mList.add("List水平");
        mList.add("List水平反向");

        mList.add("Grid垂直");
        mList.add("Grid垂直反向");
        mList.add("Grid水平");
        mList.add("Grid水平反向");

        mList.add("瀑布流垂直");
        mList.add("瀑布流垂直反向");
        mList.add("瀑布流水平");
        mList.add("瀑布流水平反向");

        // 设置ViewPager的Adapter
        mViewPager.setAdapter(new MyPagerAdapter(mList));
        // 关键一行代码，将TabLayout与ViewPager关联
        mTabLayout.setupWithViewPager(mViewPager);
    }

    class MyPagerAdapter extends PagerAdapter {

        private List<String> mList;

        public MyPagerAdapter() {

        }

        public MyPagerAdapter(List list) {
            mList = list;
        }

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(mImageList.get(position));
            return mImageList.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(mImageList.get(position));
        }

        /**
         * 重写与TabLayout配合
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return mList.get(position);
        }
    }

}
