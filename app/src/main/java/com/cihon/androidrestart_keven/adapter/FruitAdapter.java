package com.cihon.androidrestart_keven.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cihon.androidrestart_keven.R;
import com.cihon.androidrestart_keven.bean.Fruit;

import java.util.List;

/**
 * Created by zhengjian on 2017/5/10.
 */

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {
    private List<Fruit> mFruitList;
    private OnItemClickListener mOnItemClickListener;

    static class ViewHolder extends RecyclerView.ViewHolder {
        View fruitView;
        ImageView fruitImage;
        TextView fruitName;

        public ViewHolder(View itemView) {
            super(itemView);
            fruitView = itemView;
            fruitImage = (ImageView) itemView.findViewById(R.id.iv_fruit);
            fruitName = (TextView) itemView.findViewById(R.id.tv_fruit);
        }
    }

    public FruitAdapter(List<Fruit> fruitList) {
        mFruitList = fruitList;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        /**
         * 点击事件处理
         * 方法一：
         * adapter里面进行处理
         */
        holder.fruitView.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            Fruit fruit = mFruitList.get(position);
            Toast.makeText(v.getContext(), "你点击了" + fruit.getName(), Toast.LENGTH_SHORT).show();
        });
        holder.fruitImage.setOnClickListener(v -> {
            int position = holder.getAdapterPosition();
            Fruit fruit = mFruitList.get(position);
            Toast.makeText(v.getContext(), "你点击了图片" + fruit.getName(), Toast.LENGTH_SHORT).show();

        });
        /**
         * 点击事件处理
         * 方法二：
         * 接口回调方式交给Activity进行处理
         */
        if(mOnItemClickListener!=null){
            holder.fruitView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.fruitView,pos);
                }
            });

            holder.fruitView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos=holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.fruitView,pos);
                    return false;
                }
            });
        }

        return holder;
    }

    @Override
    public void onBindViewHolder(FruitAdapter.ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        holder.fruitImage.setImageResource(fruit.getImgId());
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    /**
     * 定义接口，实现点击事件回调
     */

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }
}
