package com.example.tanhao.anewbegin.inject.module;


import android.content.Context;

import com.example.tanhao.anewbegin.App;
import com.example.tanhao.anewbegin.inject.scope.ContextLife;
import com.example.tanhao.anewbegin.inject.scope.ForApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by tanhaoshi on 2017/2/22.
 */
@Module
public class ApplicationModule {

    private App mApplication;

    public ApplicationModule(App application) {
        mApplication = application;
    }

    @Provides
    @ForApp
    @ContextLife("Application")
    public Context provideApplicationContext() {
        return mApplication.getApplicationContext();
    }
}
