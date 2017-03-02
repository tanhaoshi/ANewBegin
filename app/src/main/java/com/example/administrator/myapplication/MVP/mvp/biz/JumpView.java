package com.example.administrator.myapplication.MVP.mvp.biz;

import android.content.Context;
import android.content.Intent;

import com.example.administrator.myapplication.RecycleViewActivity;

/**
 * Created by Administrator on 2017/1/12.
 */
public class JumpView implements IJumpView{
    @Override
    public void jumpActivity(Context context) {
        Intent intent = new Intent(context, RecycleViewActivity.class);
        context.startActivity(intent);
    }
}
