package com.example.tanhao.anewbegin.modules.login.module.impl;

import android.content.Context;
import android.util.Log;

import com.example.User;
import com.example.tanhao.anewbegin.Constant;
import com.example.tanhao.anewbegin.modules.login.module.LoginInteractor;
import com.example.tanhao.anewbegin.network.RequestCallBack;
import com.example.tanhao.anewbegin.sqlite.DbUtils;
import com.example.tanhao.anewbegin.utils.EncryptUtil;

import java.util.Map;

import javax.inject.Inject;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @version 1.0
 * @author TanHao
 * Created by tanhao on 2017/2/27.
 */

public class LoginInteractorImpl implements LoginInteractor<com.example.User>{

    @Inject
    public LoginInteractorImpl(){

    }

    @Override
    public Subscription login(final Map<String, String> data, final RequestCallBack<com.example.User> requestCallBack, final Context context, final String type) {

       return Observable.create(new Observable.OnSubscribe<com.example.User>() {
            @Override
            public void call(Subscriber<? super com.example.User> subscriber) {
                if(DbUtils.queryUser(data.get("userName")) >= 1){
                    User user = new User();
                    user.setCool("cool");
                    subscriber.onNext(user);
                }else{
                    subscriber.onCompleted();
                }
            }

        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<com.example.User>() {
                    @Override
                    public void onCompleted() {
                        EncryptUtil.showToast("不存在该账号,请先注册!",context);
                        requestCallBack.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("Error",e.getMessage().toString());
                        requestCallBack.onError(e.getMessage().toLowerCase(),false);
                    }

                    @Override
                    public void onNext(com.example.User user) {
                        if(type.equals(Constant.click_Login)){
                            requestCallBack.onSuccess(user);
                            EncryptUtil.showToast("成功登入!",context);
                        }else{
                            requestCallBack.onStart(user);
                        }
                    }
                });
    }
}
