package com.example.administrator.myapplication.MVP.mvp.biz;

import com.example.administrator.myapplication.MVP.mvp.bean.User;

/**
 * Created by tanhaoshi on 2017/1/9.
 */
public interface OnLoginListener {

    void loginSuccess(User user);

    void loginFailed();
}
