package com.example.administrator.myapplication.MVP.mvp.biz;

import android.content.Context;

/**
 * Created by tanhaoshi on 2017/1/9.
 */
public interface IUsetBiz {
    void login(Context context,String userName,String userPassWord,OnLoginListener listener);
}
