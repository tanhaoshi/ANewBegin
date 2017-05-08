package com.example.tanhao.anewbegin.modules.mvp.module.fragmentmodule.impl;

import com.example.tanhao.anewbegin.App;
import com.example.tanhao.anewbegin.modules.mvp.bean.LiveBaseBean;
import com.example.tanhao.anewbegin.modules.mvp.bean.LiveListItemBean;
import com.example.tanhao.anewbegin.modules.mvp.module.fragmentmodule.ShopCarInteractor;
import com.example.tanhao.anewbegin.network.NetService;
import com.example.tanhao.anewbegin.network.NetWorkUtils;
import com.example.tanhao.anewbegin.network.RequestCallBack;
import com.socks.library.KLog;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/4/25.
 */

public class ShopCarInteractorImpl implements ShopCarInteractor{

    @Inject
    public ShopCarInteractorImpl(){

    }

    @Override
    public Subscription loadLiveSourceList(int offset, int limit, String live_type, String game_type, final RequestCallBack<List<LiveListItemBean>> requestCallBack) {
        return NetWorkUtils.getInstance(App.getInstance(),2)
                .createService(NetService.class)
                .getLiveList(offset,limit,live_type,game_type)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<LiveBaseBean<List<LiveListItemBean>>>() {
                    @Override
                    public void onCompleted() {
                       requestCallBack.onCompleted();
                    }

                    @Override
                    public void onError(Throwable e) {
                        requestCallBack.onError(e.getMessage().toString() , false);
                    }

                    @Override
                    public void onNext(LiveBaseBean<List<LiveListItemBean>> listLiveBaseBean) {
                        KLog.i("集合大小:"+listLiveBaseBean.getResult().size());
                        requestCallBack.onSuccess(listLiveBaseBean.getResult());
                    }
                });
    }
}
