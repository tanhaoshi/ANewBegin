package com.example.tanhao.anewbegin.modules.login.presenter;

import com.example.tanhao.anewbegin.base.BasePresenter.PresenterLife;

import java.util.Map;

/**
 * Created by tanhaoshi on 2017/2/21.
 */

public interface LoginPresenter extends PresenterLife{
    void loginApp(Map<String,String> accomper);
}
