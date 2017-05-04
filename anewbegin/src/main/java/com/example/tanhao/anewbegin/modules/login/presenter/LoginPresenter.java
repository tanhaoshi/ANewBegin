package com.example.tanhao.anewbegin.modules.login.presenter;

import android.content.Context;

import com.example.tanhao.anewbegin.base.BasePresenter.PresenterLife;

import java.util.Map;

/**
 * @version 1.0
 * @author TanHao
 * Created by tanhaoshi on 2017/2/21.
 */

public interface LoginPresenter extends PresenterLife{
    void loginApp(Map<String,String> accomper, Context context);
    void shareLoginApp(Map<String,String> map ,Context context);
}
