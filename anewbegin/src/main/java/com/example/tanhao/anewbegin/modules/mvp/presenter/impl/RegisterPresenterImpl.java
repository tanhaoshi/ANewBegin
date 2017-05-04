package com.example.tanhao.anewbegin.modules.mvp.presenter.impl;

import android.content.Context;

import com.example.tanhao.anewbegin.base.BasePresenter.BasePresenter;
import com.example.tanhao.anewbegin.modules.mvp.module.RegisterInteractor;
import com.example.tanhao.anewbegin.modules.mvp.module.impl.RegisterInteractorImpl;
import com.example.tanhao.anewbegin.modules.mvp.presenter.RegisterPresenter;
import com.example.tanhao.anewbegin.modules.mvp.view.RegisterView;
import com.example.tanhao.anewbegin.network.RequestCallBack;

import java.util.Map;

import javax.inject.Inject;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/8.
 */

public class RegisterPresenterImpl extends BasePresenter<RegisterView, com.example.User> implements RegisterPresenter,RequestCallBack<com.example.User>{

    private RegisterInteractor<com.example.User> mInteractor;
    private Context mContext;
    //如何通过presenter将 m 与 v 绑定起来呢？
    //view 实现了一个注册的方法， 而我的Model也去实现一个注册的方法，将其绑定起来？

    @Inject
    public RegisterPresenterImpl(RegisterInteractorImpl registerInteractor, Context context){
         this.mInteractor = registerInteractor;
         this.mContext = context;
    }

    //改方法在activity中执行
    @Override
    public void toRegister(Map<String,String> content) {
        //m  这样就与 v绑定起来了
        getView().showProgress(false);
        mSubscription = mInteractor.registerApp(content,this,mContext);
    }

    @Override
    public void onSuccess(com.example.User datas) {
        super.onSuccess(datas);
        getView().toHomeActivity(datas);
        getView().hideProgress();
    }

    @Override
    public void onError(String errorMsg, boolean pullToRefresh) {
        super.onError(errorMsg, pullToRefresh);
        getView().hideProgress();
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        getView().hideProgress();
    }

}
