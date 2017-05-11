package com.example.tanhao.anewbegin.inject.component;

import com.example.tanhao.anewbegin.inject.module.ActivityModule;
import com.example.tanhao.anewbegin.modules.home.activity.HomeActivity;
import com.example.tanhao.anewbegin.modules.login.activity.LoginActivity;
import com.example.tanhao.anewbegin.modules.mvp.ui.activitys.LivePlayActivity;
import com.example.tanhao.anewbegin.modules.mvp.ui.activitys.MainActivty;
import com.example.tanhao.anewbegin.modules.mvp.ui.activitys.RegisterActivity;

import dagger.Component;

/**
 * @version 1.0
 * @author TanHao
 * Created by tanhaoshi on 2017/2/24.
 */
@Component(modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(LoginActivity activity);
    void inject(HomeActivity activity);
    void inject(RegisterActivity activity);
    void inject(MainActivty activty);
    void inject(LivePlayActivity activity);
}
