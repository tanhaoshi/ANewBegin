package com.example.tanhao.anewbegin.modules.mvp.presenter.fragmentpresenter.impl;

import com.example.tanhao.anewbegin.base.BasePresenter.BasePresenter;
import com.example.tanhao.anewbegin.modules.mvp.bean.LiveListItemBean;
import com.example.tanhao.anewbegin.modules.mvp.module.fragmentmodule.impl.ShopCarInteractorImpl;
import com.example.tanhao.anewbegin.modules.mvp.presenter.fragmentpresenter.ShopCarListPresenter;
import com.example.tanhao.anewbegin.modules.mvp.view.fragmentview.ShopCarView;
import com.example.tanhao.anewbegin.network.RequestCallBack;

import java.util.List;

import javax.inject.Inject;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/5/5.
 */

public class ShopCarListPresenterImpl extends BasePresenter<ShopCarView,List<LiveListItemBean>> implements
        RequestCallBack<List<LiveListItemBean>>,ShopCarListPresenter{

    private ShopCarInteractorImpl mShopCarInteractor;

    @Inject
    public ShopCarListPresenterImpl(ShopCarInteractorImpl shopCarInteractor){
        this.mShopCarInteractor = shopCarInteractor;
    }

    @Override
    public void loadLiveSource(int offset, int limit, String live_type, String game_type){
          mSubscription = mShopCarInteractor.loadLiveSourceList(offset , limit , live_type , game_type , this);
    }

    @Override
    public void onSuccess(List<LiveListItemBean> data) {
        super.onSuccess(data);
        if(data != null) mView.get().getLiveSource(data);
    }
}
