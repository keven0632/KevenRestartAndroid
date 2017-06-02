package com.cihon.androidrestart_keven;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cihon.androidrestart_keven.Base.MyApp;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.jude.rollviewpager.hintview.ColorPointHintView;

public class AutoImgActivity extends AppCompatActivity {

    private RollPagerView mRollViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_img);

        mRollViewPager = (RollPagerView) findViewById(R.id.roll_view_pager);
        mRollViewPager.setAnimationDurtion(500);    //设置切换时间
        mRollViewPager.setAdapter(new TestLoopAdapter(mRollViewPager)); //设置适配器
        mRollViewPager.setHintView(new ColorPointHintView(this, Color.WHITE, Color.GRAY));// 设置圆点指示器颜色

    }

    /**
     *
     * mRollViewPager.setAdapter(new TestLoopAdapter(mRollViewPager)); // 设置适配器（请求网络图片，适配器要在网络请求完成后再设置）
     * mRollViewPager.getViewPager().getAdapter().notifyDataSetChanged();// 更新banner图片
     * Picasso.with(mContext).load(bannerPictureURLs.get(position)).into(view);  // 加载网络图片
     * 在自定义适配器TestLoopAdapter的getView方法中加载网络图片
     *
     */
    private class TestLoopAdapter extends LoopPagerAdapter
    {
        private String[] imgs={"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1486175994&di=fc4fa3681ceaf8338e105ec942037945&src=http://sc.jb51.net/uploads/allimg/150703/14-150F3102113Y7.jpg",
                "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1486175994&di=ff067587f9fc2d59174e64d8dd78e873&src=http://img2.3lian.com/2014/f4/6/d/106.jpg",
                "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1486175994&di=09f6aa74181da06448784b3275964d1b&src=http://pic2.ooopic.com/12/32/19/90bOOOPIC39_1024.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1486186240829&di=848c9e42da68ff73ad5de772b7dfb9c5&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F14%2F95%2F03858PICmDk_1024.jpg"};
        //        private int[] imgs = {R.mipmap.img0, R.mipmap.img1, R.mipmap.img2, R.mipmap.img3, R.mipmap.img4,};  // 本地图片
        private int count = imgs.length;  // banner上图片的数量

        public TestLoopAdapter(RollPagerView viewPager)
        {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position)
        {
            final int picNo = position + 1;
            ImageView view = new ImageView(container.getContext());
//            view.setImageResource(imgs[position]);
            MyApp.getApplication().mImageLoader.displayImage(imgs[position],view);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            view.setOnClickListener(new View.OnClickListener()      // 点击事件
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(AutoImgActivity.this, "点击了第" + picNo + "张图片", Toast.LENGTH_SHORT).show();
                }

            });

            return view;
        }

        @Override
        public int getRealCount()
        {
            return count;
        }

    }
}
