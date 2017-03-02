package com.example.administrator.myapplication.MVP.mvp.biz;

import android.content.Context;

import com.example.administrator.myapplication.MVP.mvp.bean.User;

/**
 * Created by tanhaoshi on 2017/1/9.
 */
public class UserBiz implements IUsetBiz{

    @Override
    public void login(Context context , final String userName, final String userPassWord, final OnLoginListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }
                if("ths".equals(userName) && "123456".equals(userPassWord)){
                    User user = new User();
                    user.setUserName(userName);
                    user.setUsetPassWord(userPassWord);
                    listener.loginSuccess(user);
                }else{
                    listener.loginFailed();
                }
            }
        }).start();
    }
}
