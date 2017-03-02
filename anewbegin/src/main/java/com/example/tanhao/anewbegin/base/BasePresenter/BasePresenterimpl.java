package com.example.tanhao.anewbegin.base.BasePresenter;

import android.support.annotation.NonNull;

import com.example.tanhao.anewbegin.base.BaseView.BaseViews;
import com.example.tanhao.anewbegin.network.RequestCallBack;

import java.lang.ref.WeakReference;

import rx.Subscription;

/**
 * @version 1.0
 * @author TanHao
 * Created by admin on 2017/2/21.
 */

public class BasePresenterimpl<V extends BaseViews,T> implements PresenterLife,RequestCallBack<T>{

    public static final String TAG = "BasePresenterimpl";

    protected Subscription mSubscription; //用来取消订阅

    protected WeakReference<V> mReference; //使用弱引用 避免内存泄露

    @Override
    public void onCreate() {

    }

    @Override
    public void onBindView(@NonNull BaseViews baseViews) {
        mReference = new WeakReference<V>((V) baseViews);
    }

    @Override
    public void onStop() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onSuccess(T data) {

    }

    @Override
    public void onError(String errorMsg, boolean pullToRefresh) {

    }

    @Override
    public void onCompleted() {

    }

    @Override
    public void onProgress(long downSize, long fileSize) {

    }

    @Override
    public void dowloadSuccess(String path, String fileName, long fileSize) {

    }
}
