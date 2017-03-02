package com.example.tanhao.anewbegin.inject.component;

import com.example.tanhao.anewbegin.modules.home.activity.HomeActivity;
import com.example.tanhao.anewbegin.modules.login.activity.LoginActivity;

import dagger.Component;

/**
 * Created by tanhaoshi on 2017/2/24.
 */
@Component()
public interface ActivityComponent {
    void inject(LoginActivity activity);
    void inject(HomeActivity activity);
}
