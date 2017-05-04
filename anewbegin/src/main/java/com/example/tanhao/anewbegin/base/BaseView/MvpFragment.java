package com.example.tanhao.anewbegin.base.BaseView;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.tanhao.anewbegin.base.BasePresenter.PresenterLife;
import com.example.tanhao.anewbegin.inject.component.DaggerFragmentComponent;
import com.example.tanhao.anewbegin.inject.component.FragmentComponent;
import com.example.tanhao.anewbegin.inject.module.FragmentModule;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/27.
 */

public abstract class MvpFragment<P extends PresenterLife, V extends BaseView> extends Fragment implements BaseView{

    private Unbinder mUnbinder;
    protected P mPresenter;
    protected Bundle mBundle;
    protected FragmentComponent mFragmentComponent;
    private String TAG = "MvpFragment";

    private View rootView;//缓存Fragment view

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取bundle,并保存起来
        if (savedInstanceState != null) {
            mBundle = savedInstanceState.getBundle("bundle");
        } else {
            mBundle = getArguments() == null ? new Bundle() : getArguments();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mBundle != null) {
            outState.putBundle("bundle", mBundle);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //做双层保险 避免重复创建
        if(rootView == null){
            rootView = inflater.inflate(getContentView(),container,false);
        }else{
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null){
                parent.removeView(rootView);
            }
        }
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //在视图创建后 执行该方法
        super.onViewCreated(view, savedInstanceState);

        initInject();

        mUnbinder = ButterKnife.bind(this,view);

        initView(view);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        //解除 绑定视图的注解 ButterKnife
        mUnbinder.unbind();

        //解除与presenter的绑定 将presenter滞空 被GC回收
        if(mPresenter != null){
            mPresenter.onDestroy();
            mPresenter = null;
        }
        if(rootView!=null) rootView = null;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        //该方法只有在与viewpager连用的时候才会被调用
        //如何被调用，当前页面呈现到你眼前时候 isVisibleToUser为true
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        //当fragment为不可见的时候该方法执行
    }

    private void initInject(){
        mFragmentComponent = DaggerFragmentComponent.builder().fragmentModule(new FragmentModule(this)).build();
        initComponent();
    }


    /**
     * ------------------> 方法 <---------------------
     */
    protected abstract void initComponent();

    protected abstract int getContentView();

    protected abstract void initView(View view);

}
