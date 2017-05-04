package com.example.tanhao.anewbegin.modules.login.view;

import com.example.tanhao.anewbegin.base.BaseView.BaseView;

/**
 * @verison 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/3.
 */

public interface LoginView extends BaseView {
    void loginApp(com.example.User data);
    void shareLoginApp(com.example.User data);
}
