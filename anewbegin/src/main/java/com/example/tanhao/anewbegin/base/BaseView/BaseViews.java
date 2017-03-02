package com.example.tanhao.anewbegin.base.BaseView;

/**
 * Created by tanhao on 2017/2/16.
 */

public interface BaseViews {

    void showProgress(boolean pullToRefresh);

    void hideProgress(boolean pullToRefresh);

    void showError(String msg,boolean pullToRefresh);

    void loadData(boolean pullToRefresh);
}
