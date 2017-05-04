package com.example.tanhao.anewbegin.inject.component;

import com.example.tanhao.anewbegin.inject.module.FragmentModule;
import com.example.tanhao.anewbegin.modules.mvp.ui.fragments.BusinessFragment;

import dagger.Component;

/**
 * @verison 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/28.
 */
@Component(modules = FragmentModule.class)
public interface FragmentComponent {
    void inject(BusinessFragment businessFragment);
}
