package com.example.tanhao.anewbegin.modules.mvp.presenter.fragmentpresenter;

import com.example.tanhao.anewbegin.base.BaseView.BaseView;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/28.
 */

public interface BusinessPresenter extends BaseView{
    void getLoadDataType(String url , String id , int startPage);
}
