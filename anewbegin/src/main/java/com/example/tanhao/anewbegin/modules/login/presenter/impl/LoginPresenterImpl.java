package com.example.tanhao.anewbegin.modules.login.presenter.impl;

import android.content.Context;

import com.example.tanhao.anewbegin.Constant;
import com.example.tanhao.anewbegin.base.BasePresenter.BasePresenter;
import com.example.tanhao.anewbegin.modules.login.module.LoginInteractor;
import com.example.tanhao.anewbegin.modules.login.module.impl.LoginInteractorImpl;
import com.example.tanhao.anewbegin.modules.login.presenter.LoginPresenter;
import com.example.tanhao.anewbegin.modules.login.view.LoginView;
import com.example.tanhao.anewbegin.network.RequestCallBack;

import java.util.Map;

import javax.inject.Inject;

/**
 * @version 1.0
 * @author TanHao
 * Created by tanhaoshi on 2017/2/21.
 */

public class LoginPresenterImpl extends BasePresenter<LoginView, com.example.User> implements LoginPresenter,RequestCallBack<com.example.User>{

    private LoginInteractor<com.example.User>  mLoginInteractor;

    @Inject
    public LoginPresenterImpl(LoginInteractorImpl loginInteractorImpl){
          this.mLoginInteractor = loginInteractorImpl;
    }

    @Override
    public void loginApp(Map<String, String> accomper, Context context) {
        getView().showProgress(false);
        mSubscription = mLoginInteractor.login(accomper,this,context, Constant.click_Login);
    }

    @Override
    public void shareLoginApp(Map<String, String> map, Context context) {
        getView().showProgress(false);
        mSubscription = mLoginInteractor.login(map,this,context,Constant.share_login);
    }

    @Override
    public void onCompleted() {
        super.onCompleted();
        getView().hideProgress();
    }

    @Override
    public void onSuccess(com.example.User data) {
        super.onSuccess(data);
        getView().hideProgress();
        getView().loginApp(data);
    }

    @Override
    public void onError(String errorMsg, boolean pullToRefresh) {
        super.onError(errorMsg, pullToRefresh);
        getView().hideProgress();
    }

    @Override
    public void onStart(com.example.User user) {
        super.onStart(user);
        getView().hideProgress();
        getView().shareLoginApp(user);
    }
}
