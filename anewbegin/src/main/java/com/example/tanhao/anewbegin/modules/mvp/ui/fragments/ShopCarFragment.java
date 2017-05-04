package com.example.tanhao.anewbegin.modules.mvp.ui.fragments;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.example.tanhao.anewbegin.App;
import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BaseView.MvpFragment;
import com.example.tanhao.anewbegin.modules.mvp.ui.adapters.PostFragmentAdapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;


/**
 * @verison 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/13.
 */

public class ShopCarFragment extends MvpFragment {

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;
    @BindView(R.id.image_add)
    ImageView mImageView;
    @BindView(R.id.view_pager)
    ViewPager mViewPager;
    private final List<String> liveNameList = new ArrayList<>();
    private final List<Fragment> mFragmentList = new ArrayList<>();

    @Override
    protected void initComponent() {

    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_shapecar;
    }

    @Override
    protected void initView(View view) {
        setFragmentName();
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

    private void setFragmentName(){
        Collections.addAll(liveNameList, App.getInstance().getResources().getStringArray(R.array.live_title));
        if(liveNameList !=null ) setLiveNameList(liveNameList);
        setViewPager(liveNameList , mFragmentList);
    }

    private void setLiveNameList(List<String> list){
        mFragmentList.clear();
        int count = list.size();
        for(int i = 0 ; i<count ; i++){
            ShopCarListFragment fragment = ShopCarListFragment.newInstance(list.get(i),i);
            mFragmentList.add(fragment);
        }
    }

    private void setViewPager(List<String> listTitle , List<Fragment> fragmentList){
        PostFragmentAdapter postFragmentAdapter = new PostFragmentAdapter(getFragmentManager(),fragmentList,listTitle);
        mViewPager.setAdapter(postFragmentAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.black));
        mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
}
