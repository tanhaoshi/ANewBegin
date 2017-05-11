package com.example.tanhao.anewbegin.modules.mvp.presenter.fragmentpresenter.impl;

import com.example.tanhao.anewbegin.base.BasePresenter.BasePresenter;
import com.example.tanhao.anewbegin.modules.mvp.bean.NewsSummary;
import com.example.tanhao.anewbegin.modules.mvp.module.fragmentmodule.impl.BusinessInteractorImpl;
import com.example.tanhao.anewbegin.modules.mvp.presenter.fragmentpresenter.BusinessPresenter;
import com.example.tanhao.anewbegin.modules.mvp.view.fragmentview.BusinessView;
import com.example.tanhao.anewbegin.network.RequestCallBack;

import java.util.List;

import javax.inject.Inject;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/28.
 */

public class BusinessPresenterImpl extends BasePresenter<BusinessView,List<NewsSummary>> implements
        BusinessPresenter,RequestCallBack<List<NewsSummary>>{

    private BusinessInteractorImpl mBusinessInteractor;

    @Inject
    public BusinessPresenterImpl(BusinessInteractorImpl businessInteractor){
        this.mBusinessInteractor = businessInteractor;
    }

    @Override
    public void getLoadDataType(String type , String id , int startPage) {
        mSubscription = mBusinessInteractor.getLoadDataType(type,id,startPage,this);
    }

    @Override
    public void onSuccess(List<NewsSummary> data) {
        super.onSuccess(data);
        mView.get().loadSussec(data);
    }
}
