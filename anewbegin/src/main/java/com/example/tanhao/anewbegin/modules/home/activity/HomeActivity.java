package com.example.tanhao.anewbegin.modules.home.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.graphics.Palette;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BaseActivity;
import com.example.tanhao.anewbegin.modules.home.presenter.impl.HomePresenterImpl;
import com.example.tanhao.anewbegin.modules.mvp.ui.fragments.BusinessFragment;
import com.example.tanhao.anewbegin.modules.mvp.ui.fragments.ShopCarFragment;
import com.example.tanhao.anewbegin.modules.mvp.ui.fragments.UserFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * @version 1.0
 * @author TanHao
 * Created by admin on 2017/2/21.
 */

public class HomeActivity extends BaseActivity<HomePresenterImpl> implements BusinessFragment.OnListenerRGB{

    private long oldOutTime;

    @BindView(R.id.ahb_navigation)
    AHBottomNavigation mNavigation;
    @BindView(R.id.fragment_flag)
    FrameLayout flag;

    private BusinessFragment mBusinessFragment;
    private ShopCarFragment mShopCarFragment;
    private UserFragment mUserFragment;

    protected android.support.v4.app.Fragment curentFragment;

    private FragmentManager mFragmentManager;

    private ArrayList<Integer> imagesList;
    private int[] image = new int[]{R.drawable.beach,R.drawable.beijing,R.drawable.milu,R.drawable.love};

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        initTabs();
        initFirstFragment();
        initListenner();
    }

    @Override
    protected void inject() {
        component.inject(this);
    }

    @Override
    protected void initListener() {

    }

    private void initTabs(){
        AHBottomNavigationItem item = new AHBottomNavigationItem("商业",R.drawable.tab_subject);
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("购物车",R.drawable.tab_sport);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("我的",R.drawable.tab_me);

        mNavigation.addItem(item);
        mNavigation.addItem(item1);
        mNavigation.addItem(item2);

        mNavigation.setAccentColor(ContextCompat.getColor(this, R.color.accent_bottom_navigation));
        mNavigation.setInactiveColor(ContextCompat.getColor(this, R.color.inactive_bottom_navigation));

        mNavigation.setDefaultBackgroundColor(ContextCompat.getColor(this, R.color.bg_bottom_navigation));

        // 强制着色
        mNavigation.setForceTint(true);
        // 使用圆圈效果
        mNavigation.setColored(false);
        // Set current item programmatically
        mNavigation.setCurrentItem(0);
    }

    private void initFirstFragment(){

        mFragmentManager = getSupportFragmentManager();

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();

        if(mBusinessFragment == null){
            mBusinessFragment = new BusinessFragment();
            Bundle bundle = new Bundle();
            bundle.putIntegerArrayList("imageList",bannerData());
            mBusinessFragment.setArguments(bundle);
            mBusinessFragment.setListenerRGB(this);
        }

        //判断当前activity容器中碎片是否是它
        if(!mBusinessFragment.isAdded()){
            //添加到当前容器，并显示它
            fragmentTransaction.add(R.id.fragment_flag ,mBusinessFragment).commit();

            curentFragment = mBusinessFragment;
        }

        title.setText("商业");
    }

    private void initListenner(){
        mNavigation.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {
                chooseTabs(position);
                return true;
            }
        });
    }

    private void chooseTabs(int position){
        switch (position){
            case 0:
                if(mBusinessFragment == null){
                   mBusinessFragment = new BusinessFragment();
                }
                addOrShowFragment(mBusinessFragment);
                break;
            case 1:
                if(mShopCarFragment == null){
                    mShopCarFragment = new ShopCarFragment();
                }
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                addOrShowFragment(mShopCarFragment);
                break;
            case 2:
                if(mUserFragment == null){
                    mUserFragment = new UserFragment();
                }
                toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                addOrShowFragment(mUserFragment);
                break;
        }
    }

    /**
     * 添加或者显示 fragment
     * @param fragment
     */
    private void addOrShowFragment(Fragment fragment){
        if(curentFragment == fragment) return ;

        FragmentTransaction transaction = mFragmentManager.beginTransaction();

        //判断点击后的碎片是否添加到容器中
        if(!fragment.isAdded()){
            //没有添加进来
            transaction.hide(curentFragment).add(R.id.fragment_flag,fragment).commit();
        }else{
            //已经添加进来了 ， 只需要显示下就行
            transaction.hide(curentFragment).show(fragment).commit();
        }

        curentFragment = fragment;

        setToolbar();
    }

    private void setToolbar(){
        if(curentFragment instanceof BusinessFragment){
            title.setText("商业");
        }else if(curentFragment instanceof ShopCarFragment){
            title.setText("购物车");
        }else if(curentFragment instanceof UserFragment){
            title.setText("我的");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if( System.currentTimeMillis() - oldOutTime > 2000){
                oldOutTime = System.currentTimeMillis();
                Toast.makeText(HomeActivity.this,"再次点击，退出应用",Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onGetRGB(final int position) {
        Bitmap bitmap = null;
        if(position == 1){
            bitmap = BitmapFactory.decodeResource(getResources(),imagesList.get(0));
        }else if(position == 2){
            bitmap = BitmapFactory.decodeResource(getResources(),imagesList.get(1));
        }else if(position == 3){
            bitmap = BitmapFactory.decodeResource(getResources(),imagesList.get(2));
        }else if(position == 4){
            bitmap = BitmapFactory.decodeResource(getResources(),imagesList.get(3));
        }else if(position == 5){
            bitmap = BitmapFactory.decodeResource(getResources(),imagesList.get(0));
        }
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                if(curentFragment instanceof ShopCarFragment){
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else if(curentFragment instanceof UserFragment){
                    toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                }else {
                    Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                    toolbar.setBackgroundColor(vibrantSwatch.getRgb());
                }
            }
        });
    }

    private ArrayList bannerData(){
        imagesList = new ArrayList<>();
        for(int i:image){
            imagesList.add(i);
        }
        return imagesList;
    }

}
