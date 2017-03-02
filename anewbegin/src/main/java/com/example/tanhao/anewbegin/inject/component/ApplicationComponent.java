package com.example.tanhao.anewbegin.inject.component;

import android.content.Context;

import com.example.tanhao.anewbegin.inject.module.ApplicationModule;
import com.example.tanhao.anewbegin.inject.scope.ContextLife;
import com.example.tanhao.anewbegin.inject.scope.ForApp;

import dagger.Component;

/**
 * Created by tanhaoshi on 2017/2/22.
 */
@ForApp
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

     @ContextLife("Application")
     Context getApplication();
}
