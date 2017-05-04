package com.example.tanhao.anewbegin.network;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/31.
 */

public interface ResponseCallBack<T> {

    void onStart();

    void onCompleted();

    void onError(Throwable e);

    void onSuccee(T response);
}
