package com.example.tanhao.anewbegin.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.tanhao.anewbegin.R;
import com.youth.banner.loader.ImageLoader;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/13.
 */

public class GildeImageLoader extends ImageLoader{
    @Override
    public void displayImage(Context context, Object path, ImageView imageView) {
        Glide.with(context).load(path)
                .placeholder(R.drawable.img_two_bi_one)
                .error(R.drawable.img_two_bi_one)
                .crossFade(1000)
                .into(imageView);
    }
}
