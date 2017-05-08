package com.example.tanhao.anewbegin.modules.mvp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.tanhao.anewbegin.Constant;
import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BaseView.MvpFragment;
import com.example.tanhao.anewbegin.modules.mvp.presenter.fragmentpresenter.impl.ShopCarListPresenterImpl;
import com.example.tanhao.anewbegin.modules.mvp.view.fragmentview.ShopCarView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/4/27.
 */

public class ShopCarListFragment extends MvpFragment<ShopCarListPresenterImpl , ShopCarView> implements ShopCarView{

    @Inject
    ShopCarListPresenterImpl mShopCarListPresenter;

    @BindView(R.id.shop_refresh)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.shop_recyclerview)
    RecyclerView mRecyclerView;

    private String mGameType , mGameTitle;
    private int offset = 0;
    int limit = 20;

    public static ShopCarListFragment newInstance(String gameType , String gameTitle){
        ShopCarListFragment fragment = new ShopCarListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(Constant.GAME_TYPE,gameType);
        bundle.putString(Constant.GAME_TITLE,gameTitle);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null) mGameTitle = getArguments().getString(Constant.GAME_TITLE);
    }

    @Override
    protected void initComponent() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_shopcarlist;
    }

    @Override
    protected void initView(View view) {

        mPresenter = mShopCarListPresenter;

        mPresenter.onBindView(this);

        if(getUserVisibleHint()){
            loadData(false);
        }
    }

    @Override
    public void getLiveSource(List<?> list) {

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
       mShopCarListPresenter.loadLiveSource(offset,limit,"",mGameTitle);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
           if(mPresenter != null){
               loadData(false);
           }
        }
    }

}
