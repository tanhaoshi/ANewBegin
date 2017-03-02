package com.example.administrator.myapplication.MVP.mvp.presenter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;

import com.example.administrator.myapplication.MVP.mvp.bean.User;
import com.example.administrator.myapplication.MVP.mvp.biz.IUsetBiz;
import com.example.administrator.myapplication.MVP.mvp.biz.OnLoginListener;
import com.example.administrator.myapplication.MVP.mvp.biz.UserBiz;
import com.example.administrator.myapplication.MVP.mvp.view.IUserLoginView;
import com.example.administrator.myapplication.PhotoPacage.PhotoActivity;

/**
 * Created by tanhaoshi on 2017/1/9.
 */
public class UserLoginPresenter {

    private IUsetBiz usetBiz;
    private IUserLoginView userLoginView;
    private Handler mhandler = new Handler();
    private Context context;

    public UserLoginPresenter(IUserLoginView userLoginView,Context context){
        this.usetBiz = new UserBiz();
        this.userLoginView = userLoginView;
        this.context = context;
    }

    public void login(){
        usetBiz.login(context,userLoginView.getUserName(), userLoginView.getUserPassword(), new OnLoginListener() {
            @Override
            public void loginSuccess(final User user) {
               mhandler.post(new Runnable() {
                   @Override
                   public void run() {
                       userLoginView.toMainActivity(user);
                       userLoginView.showMessage("登入成功!");
                       Intent intent = new Intent(context, PhotoActivity.class);
                       context.startActivity(intent);
                   }
               });
            }

            @Override
            public void loginFailed() {
               mhandler.post(new Runnable() {
                   @Override
                   public void run() {
                       userLoginView.showMessage("登入失败!");
                   }
               });
            }
        });
    }
}
