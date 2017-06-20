package com.cihon.androidrestart_keven.activity;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.cihon.androidrestart_keven.Base.MyApp;
import com.cihon.androidrestart_keven.R;
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
        mRollViewPager.setAnimationDurtion(500);    //�����л�ʱ��
        mRollViewPager.setAdapter(new TestLoopAdapter(mRollViewPager)); //����������
        mRollViewPager.setHintView(new ColorPointHintView(this, Color.WHITE, Color.GRAY));// ����Բ��ָʾ����ɫ

    }

    /**
     *
     * mRollViewPager.setAdapter(new TestLoopAdapter(mRollViewPager)); // ��������������������ͼƬ��������Ҫ������������ɺ������ã�
     * mRollViewPager.getViewPager().getAdapter().notifyDataSetChanged();// ����bannerͼƬ
     * Picasso.with(mContext).load(bannerPictureURLs.get(position)).into(view);  // ��������ͼƬ
     * ���Զ���������TestLoopAdapter��getView�����м�������ͼƬ
     *
     */
    private class TestLoopAdapter extends LoopPagerAdapter
    {
        private String[] imgs={"https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1486175994&di=fc4fa3681ceaf8338e105ec942037945&src=http://sc.jb51.net/uploads/allimg/150703/14-150F3102113Y7.jpg",
                "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1486175994&di=ff067587f9fc2d59174e64d8dd78e873&src=http://img2.3lian.com/2014/f4/6/d/106.jpg",
                "https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1486175994&di=09f6aa74181da06448784b3275964d1b&src=http://pic2.ooopic.com/12/32/19/90bOOOPIC39_1024.jpg",
                "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1486186240829&di=848c9e42da68ff73ad5de772b7dfb9c5&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F14%2F95%2F03858PICmDk_1024.jpg"};
        //        private int[] imgs = {R.mipmap.img0, R.mipmap.img1, R.mipmap.img2, R.mipmap.img3, R.mipmap.img4,};  // ����ͼƬ
        private int count = imgs.length;  // banner��ͼƬ������

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
            view.setOnClickListener(new View.OnClickListener()      // ����¼�
            {
                @Override
                public void onClick(View v)
                {
                    Toast.makeText(AutoImgActivity.this, "����˵�" + picNo + "��ͼƬ", Toast.LENGTH_SHORT).show();
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
