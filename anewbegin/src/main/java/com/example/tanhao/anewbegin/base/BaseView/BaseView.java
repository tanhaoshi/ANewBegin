package com.example.tanhao.anewbegin.base.BaseView;

/**
 * @verison 1.0
 * @author TanHao
 * Created by admin on 2017/2/16.
 */

public interface BaseView {

    void showProgress(boolean isTrue);

    void hideProgress();

    void showError(String msg,boolean pullToRefresh);

    void loadData(boolean pullToRefresh);
}
