package com.example.tanhao.anewbegin.modules.mvp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.ScrollView;

import com.example.tanhao.anewbegin.App;
import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BaseView.MvpFragment;
import com.example.tanhao.anewbegin.layout.layoutManager.DividerGridItemDecoration;
import com.example.tanhao.anewbegin.layout.layoutManager.ExStaggeredGridLayoutManager;
import com.example.tanhao.anewbegin.layout.layoutManager.FullyGridLayoutManager;
import com.example.tanhao.anewbegin.modules.mvp.bean.NewsSummary;
import com.example.tanhao.anewbegin.modules.mvp.presenter.fragmentpresenter.impl.BusinessPresenterImpl;
import com.example.tanhao.anewbegin.modules.mvp.ui.adapters.FraBusinessAdapter;
import com.example.tanhao.anewbegin.modules.mvp.ui.adapters.StaggeredAdapter;
import com.example.tanhao.anewbegin.modules.mvp.view.fragmentview.BusinessView;
import com.example.tanhao.anewbegin.utils.GildeImageLoader;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * @version 1.0
 * @author  TanHao
 * Created by Administrator on 2017/3/13.
 */

public class BusinessFragment extends MvpFragment<BusinessPresenterImpl,BusinessView> implements BusinessView{

    @BindView(R.id.banner)
    Banner mBanner;
    @BindView(R.id.scrollView_id)
    ScrollView mScrollView;
    @BindView(R.id.recycler_business)
    RecyclerView mRecyclerView;
    @BindView(R.id.recycler_staggerd)
    RecyclerView recyclviewStraggerd;

    private OnListenerRGB mOnListenerRGB;

    @Inject
    BusinessPresenterImpl mBusinessPresenter;

    private ArrayList mArrayList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if(bundle != null) {
            mArrayList = bundle.getIntegerArrayList("imageList");
        }
    }

    @Override
    protected void initComponent() {
        mFragmentComponent.inject(this);
    }

    @Override
    protected int getContentView() {
        return R.layout.fragment_business;
    }

    @Override
    protected void initView(View view) {

         mPresenter = mBusinessPresenter;

         mPresenter.onBindView(this);

         loadData(false);
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
        initData();
    }

    @Override
    public void loadSussec(List<NewsSummary> data) {
        FraBusinessAdapter fraBusinessAdapter = new FraBusinessAdapter(R.layout.fragment_adapter_business,data.get(0).getAds());
        //RecycleView假设没有设置LayoutManger()程序将不会出现适配器
        FullyGridLayoutManager fullyGridLayoutManager = new FullyGridLayoutManager(App.getInstance(),3);
        fullyGridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(fullyGridLayoutManager);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.addItemDecoration(new DividerGridItemDecoration(App.getInstance()));
        mRecyclerView.setAdapter(fraBusinessAdapter);
        View view = View.inflate(App.getInstance(),R.layout.baseadapter_header_layout,null);
        fraBusinessAdapter.addHeaderView(view);
        recommendArea(data);
    }

    private void initData(){
        banerData();
        mBusinessPresenter.getLoadDataType("headline","T1348647909107",0);
    }

    private void banerData(){
        mBanner.isAutoPlay(true);
        mBanner.setDelayTime(4500);
        mBanner.setIndicatorGravity(BannerConfig.RIGHT);
        mBanner.setImages(mArrayList).setImageLoader(new GildeImageLoader()).start();
        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
                  mOnListenerRGB.onGetRGB(position);
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public void setListenerRGB(OnListenerRGB onListenerRGB){
        this.mOnListenerRGB = onListenerRGB;
    }

    public interface OnListenerRGB{
        void onGetRGB(int position);
    }

    private void recommendArea(List<NewsSummary> list){
        StaggeredAdapter staggeredAdapter = new StaggeredAdapter(R.layout.fragment_adapter_staggered,list);
        ExStaggeredGridLayoutManager manager = new ExStaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclviewStraggerd.setLayoutManager(manager);
        recyclviewStraggerd.addItemDecoration(new DividerGridItemDecoration(App.getInstance()));
        //该方法解决scrollview嵌套recyclerview滑动不流程问题。。 还有一种方案就是重写scrollview
        recyclviewStraggerd.setNestedScrollingEnabled(false);
        recyclviewStraggerd.setAdapter(staggeredAdapter);
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser){
            if(mBanner != null){
                mBanner.startAutoPlay();
            }
        }else if(mBanner != null){
            mBanner.stopAutoPlay();
        }
    }
}
