package com.example.tanhao.anewbegin.modules.mvp.module.impl;

import com.example.tanhao.anewbegin.App;
import com.example.tanhao.anewbegin.modules.mvp.bean.LiveBaseBean;
import com.example.tanhao.anewbegin.modules.mvp.bean.LiveDetailBean;
import com.example.tanhao.anewbegin.modules.mvp.module.LivePlayInteractor;
import com.example.tanhao.anewbegin.network.NetService;
import com.example.tanhao.anewbegin.network.NetWorkUtils;
import com.example.tanhao.anewbegin.network.RequestCallBack;
import com.socks.library.KLog;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/5/10.
 */

public class LivePlayInteractorImpl implements LivePlayInteractor{

    @Inject
    public LivePlayInteractorImpl(){

    }

    @Override
    public Subscription getLiveStream(String live_type, String live_id, String game_type, final RequestCallBack<LiveDetailBean> requestCallBack) {
        return NetWorkUtils.getInstance(App.getInstance(),2)
                .createService(NetService.class)
                .getLiveDetail(live_type ,live_id,game_type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LiveBaseBean<LiveDetailBean>>() {
                    @Override
                    public void onCompleted() {
                      requestCallBack.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                       requestCallBack.onError(e.getMessage(),true);
                    }

                    @Override
                    public void onNext(LiveBaseBean<LiveDetailBean> liveDetailBeanLiveBaseBean) {
                        KLog.i("程序进来了几次？");
                        requestCallBack.onSuccess(liveDetailBeanLiveBaseBean.getResult());
                    }
                });
    }
}
