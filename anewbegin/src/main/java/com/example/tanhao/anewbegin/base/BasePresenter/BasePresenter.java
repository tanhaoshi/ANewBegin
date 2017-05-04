package com.example.tanhao.anewbegin.base.BasePresenter;

import android.support.annotation.NonNull;

import com.example.tanhao.anewbegin.base.BaseView.BaseView;
import com.example.tanhao.anewbegin.network.RequestCallBack;
import com.example.tanhao.anewbegin.utils.AppUtils;

import java.lang.ref.WeakReference;

import rx.Subscription;

/**
 * @version 1.0
 * @author TanHao
 * Created by admin on 2017/2/21.
 */

public class BasePresenter<V extends BaseView,T> implements PresenterLife,RequestCallBack<T>{

    protected Subscription mSubscription; //用来取消订阅

    protected WeakReference<V> mView; //使用弱引用 避免内存泄露

    @Override
    public void onCreate() {

    }

    @Override
    public void onBindView(@NonNull BaseView baseView) {
        mView = new WeakReference<V>((V) baseView);
    }

    @Override
    public void onDestroy() {
         if(mView != null){
             mView.clear();
             mView = null;
         }
        AppUtils.cancelSubscription(mSubscription);
        mSubscription = null;
    }

    @Override
    public void onStart(T data) {

    }

    @Override
    public void onSuccess(T data) {
        mView.get().hideProgress();
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

    @NonNull
    public V getView(){
        return mView==null ? null : mView.get();
    }
}
