package com.example.tanhao.anewbegin.modules.login.module;

import android.content.Context;

import com.example.tanhao.anewbegin.network.RequestCallBack;

import java.util.Map;

import rx.Subscription;

/**
 * @version 1.0
 * @author TanHao
 * Created by tanhao on 2017/2/27.
 */

public interface LoginInteractor<T> {
    Subscription login(Map<String,String> data, RequestCallBack<T> requestCallBack, Context context,String type);
}
