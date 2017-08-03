package com.cihon.androidrestart_keven.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.adapter.FruitAdapter;
import com.cihon.androidrestart_keven.bean.Fruit;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyleViewActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<Fruit> mFruitList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyle_view);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        initFruits();//初始化水果数据
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(mFruitList);
        mRecyclerView.setAdapter(adapter);

        Button bt_day = (Button) findViewById(R.id.bt_day);
        Button bt_night = (Button) findViewById(R.id.bt_night);
        bt_day.setOnClickListener(v -> getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO));
        bt_night.setOnClickListener(v -> getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES));


        /**
         * 在activity中实现接口，进行相应的点击事件处理
         */
        adapter.setOnItemClickListener(new FruitAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

        //设置分割线
//        mRecyclerView.addItemDecoration(new DividerItemDecoration(RecyleViewActivity.this, DividerItemDecoration.HORIZONTAL_LIST));
    }

    public String getRandomLengthName(String name) {
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            builder.append(name);
        }
        return builder.toString();
    }

    private void initFruits() {
        for (int i = 0; i < 2; i++) {
            Fruit apple = new Fruit(getRandomLengthName("Apple"), R.drawable.apple_pic);
            mFruitList.add(apple);
            Fruit banana = new Fruit(getRandomLengthName("Banana"), R.drawable.banana_pic);
            mFruitList.add(banana);
            Fruit orange = new Fruit(getRandomLengthName("Orange"), R.drawable.orange_pic);
            mFruitList.add(orange);
            Fruit watermelon = new Fruit(getRandomLengthName("Watermelon"), R.drawable.watermelon_pic);
            mFruitList.add(watermelon);
            Fruit pear_pic = new Fruit(getRandomLengthName("Pear"), R.drawable.pear_pic);
            mFruitList.add(pear_pic);
            Fruit grape_pic = new Fruit(getRandomLengthName("Grape"), R.drawable.grape_pic);
            mFruitList.add(grape_pic);
            Fruit pineapple_pic = new Fruit(getRandomLengthName("Pineapple"), R.drawable.pineapple_pic);
            mFruitList.add(pineapple_pic);

            Fruit strawberry_pic = new Fruit(getRandomLengthName("Strawberry"), R.drawable.strawberry_pic);
            mFruitList.add(strawberry_pic);
            Fruit cherry_pic = new Fruit(getRandomLengthName("Cherry"), R.drawable.cherry_pic);
            mFruitList.add(cherry_pic);
            Fruit mango_pic = new Fruit(getRandomLengthName("Mango"), R.drawable.mango_pic);
            mFruitList.add(mango_pic);


        }

    }
}
