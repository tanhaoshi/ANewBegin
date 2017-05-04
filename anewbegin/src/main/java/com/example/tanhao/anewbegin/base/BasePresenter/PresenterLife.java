package com.example.tanhao.anewbegin.base.BasePresenter;

import android.support.annotation.NonNull;

import com.example.tanhao.anewbegin.base.BaseView.BaseView;

/**
 * Created by tanhao on 2017/2/16.
 */

public interface PresenterLife {

    void onCreate();

    void onBindView(@NonNull BaseView baseView);

    void onDestroy();
}
