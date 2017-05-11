package com.example.tanhao.anewbegin.modules.mvp.presenter.impl;

import com.example.tanhao.anewbegin.base.BasePresenter.BasePresenter;
import com.example.tanhao.anewbegin.modules.mvp.bean.LiveDetailBean;
import com.example.tanhao.anewbegin.modules.mvp.module.impl.LivePlayInteractorImpl;
import com.example.tanhao.anewbegin.modules.mvp.presenter.LivePlayPresenter;
import com.example.tanhao.anewbegin.modules.mvp.view.LivePlayView;
import com.example.tanhao.anewbegin.network.RequestCallBack;

import javax.inject.Inject;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/5/10.
 */

public class LivePlayPresenterImpl extends BasePresenter<LivePlayView ,LiveDetailBean> implements
        RequestCallBack<LiveDetailBean>,LivePlayPresenter{


    private LivePlayInteractorImpl mLivePlayInteractor;

    @Inject
    public LivePlayPresenterImpl(LivePlayInteractorImpl livePlayInteractor){
        this.mLivePlayInteractor = livePlayInteractor;
    }

    @Override
    public void getLiveDetail(String live_type, String live_id, String game_type) {
        mSubscription = mLivePlayInteractor.getLiveStream(live_type,live_id,game_type,this);
    }

    @Override
    public void onSuccess(LiveDetailBean data) {
        super.onSuccess(data);
        if(data!=null)getView().getLiveDataDetail(data);
    }
}
