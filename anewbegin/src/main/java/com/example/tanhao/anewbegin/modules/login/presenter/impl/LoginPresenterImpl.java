package com.example.tanhao.anewbegin.modules.login.presenter.impl;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.tanhao.anewbegin.base.BasePresenter.PresenterLife;
import com.example.tanhao.anewbegin.base.BaseView.BaseViews;
import com.example.tanhao.anewbegin.utils.User;

import javax.inject.Inject;

/**
 * Created by tanhaoshi on 2017/2/21.
 */

public class LoginPresenterImpl implements PresenterLife{

    User mUser;

    @Inject
    public LoginPresenterImpl(User user){
        Log.i("LoginPresenterImpl","程序进来了吗？");
        this.mUser = user;
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onBindView(@NonNull BaseViews baseViews) {

    }

    @Override
    public void onStop() {

    }

    public String backString(){
        return "今晚打老虎";
    }
}
