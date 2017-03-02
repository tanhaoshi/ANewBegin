package com.example.administrator.myapplication.MVP.mvp.presenter;

import android.content.Context;

import com.example.administrator.myapplication.MVP.mvp.biz.IJumpView;
import com.example.administrator.myapplication.MVP.mvp.biz.JumpView;

/**
 * Created by tanhaoshi on 2017/1/12.
 */
public class JumpViewPresenter {
    private IJumpView jumpView;
    private Context context;

    public JumpViewPresenter(Context context){
        jumpView = new JumpView();
        this.context = context;
    }

    public void jump(){
        jumpView.jumpActivity(context);
    }
}
