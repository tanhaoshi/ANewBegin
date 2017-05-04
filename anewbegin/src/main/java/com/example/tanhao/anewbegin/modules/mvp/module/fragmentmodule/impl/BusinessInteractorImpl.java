package com.example.tanhao.anewbegin.modules.mvp.module.fragmentmodule.impl;

import com.example.tanhao.anewbegin.App;
import com.example.tanhao.anewbegin.modules.mvp.bean.NewsSummary;
import com.example.tanhao.anewbegin.modules.mvp.module.fragmentmodule.BusinessInteractor;
import com.example.tanhao.anewbegin.network.NetService;
import com.example.tanhao.anewbegin.network.NetWorkUtils;
import com.example.tanhao.anewbegin.network.OkHttpUtil;
import com.example.tanhao.anewbegin.network.RequestCallBack;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/28.
 */

public class BusinessInteractorImpl implements BusinessInteractor{

    @Inject
    public BusinessInteractorImpl(){

    }

    @Override
    public Subscription getLoadDataType(String url, String id, int startPage, final RequestCallBack<List<NewsSummary>> requestCallBack) {
        return NetWorkUtils.getInstance(App.getInstance())
                .createService(NetService.class)
                .getNewLists(OkHttpUtil.getCacheControl(),url,id,startPage)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Map<String, List<NewsSummary>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Map<String, List<NewsSummary>> stringListMap) {
                         requestCallBack.onSuccess(stringListMap.get("T1348647909107"));
                    }
                });
    }
}
