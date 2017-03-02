package com.example.administrator.myapplication.MVP.mvp.view;

import com.example.administrator.myapplication.MVP.mvp.bean.User;

/**
 * Created by tanhaoshi on 2017/1/9.
 */
public interface IUserLoginView {
    String getUserName();
    String getUserPassword();
    void showMessage(String message);
    void toMainActivity(User user);
}
