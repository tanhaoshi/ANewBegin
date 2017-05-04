package com.example.tanhao.anewbegin.modules.mvp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.tanhao.anewbegin.Constant;
import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BaseView.MvpFragment;
import com.socks.library.KLog;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/4/27.
 */

public class ShopCarListFragment extends MvpFragment{

    public static ShopCarListFragment newInstance(String gameType , int index){
        ShopCarListFragment fragment = new ShopCarListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.GAME_TYPE,gameType);
        bundle.putInt(Constant.GAME_INDEX,index);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String str = null;
        if(getArguments() != null) str = getArguments().getString(Constant.GAME_TYPE);
        if (str != null) KLog.i(str);
    }

    @Override
    protected void initComponent() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_shopcarlist;
    }

    @Override
    protected void initView(View view) {

    }

    @Override
    public void showProgress(boolean isTrue) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String msg, boolean pullToRefresh) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {

    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
    }
}
