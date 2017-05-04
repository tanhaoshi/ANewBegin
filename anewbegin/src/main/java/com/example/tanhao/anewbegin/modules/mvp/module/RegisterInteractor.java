package com.example.tanhao.anewbegin.modules.mvp.module;

import android.content.Context;

import com.example.User;
import com.example.tanhao.anewbegin.network.RequestCallBack;

import java.util.Map;

import rx.Subscription;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/8.
 */

public interface RegisterInteractor<T> {
    Subscription registerApp(Map<String,String> content, RequestCallBack<User> callBack, Context context);
}
