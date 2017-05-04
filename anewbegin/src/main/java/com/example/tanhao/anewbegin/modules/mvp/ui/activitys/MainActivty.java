package com.example.tanhao.anewbegin.modules.mvp.ui.activitys;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Toast;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.example.tanhao.anewbegin.App;
import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BaseActivity;
import com.example.tanhao.anewbegin.modules.mvp.presenter.impl.MainPresenterImpl;
import com.example.tanhao.anewbegin.modules.mvp.ui.adapters.FragmentAdapter;
import com.example.tanhao.anewbegin.modules.mvp.ui.fragments.BusinessFragment;
import com.example.tanhao.anewbegin.modules.mvp.ui.fragments.ShopCarFragment;
import com.example.tanhao.anewbegin.modules.mvp.ui.fragments.UserFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivty extends BaseActivity<MainPresenterImpl> implements BusinessFragment.OnListenerRGB{

    private long oldOutTime;

    @BindView(R.id.ahb_navigation)
    AHBottomNavigation mNavigation;
    @BindView(R.id.fragment_flag)
    ViewPager flag;

    private BusinessFragment mBusinessFragment;
    private ShopCarFragment mShopCarFragment;
    private UserFragment mUserFragment;

    private ArrayList<Fragment> mList;
    private ArrayList<Integer> imagesList;
    private int[] image = new int[]{R.drawable.beach,R.drawable.beijing,R.drawable.milu,R.drawable.love};

    private int temporary;

    @Override
    protected int getContentView() {
        return R.layout.activity_home;
    }

    @Override
    protected void initView() {
        initTabs();
        initFragemnt();
        initFirstToolBarBackgground();
        initEditextAnimotion();
    }

    @Override
    protected void inject() {
        component.inject(this);
    }

    @Override
    protected void initListener() {
        initListenner();
    }

    @Override
    public void onGetRGB(int position) {
        Bitmap bitmap = null;
        if(position == 1){
            bitmap  = BitmapFactory.decodeResource(getResources(),imagesList.get(0));
        }else if(position == 2){
            bitmap = BitmapFactory.decodeResource(getResources(), imagesList.get(1));
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
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                toolbar.setBackgroundColor(vibrantSwatch.getRgb());
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) getWindow().setStatusBarColor(vibrantSwatch.getRgb());
                temporary = vibrantSwatch.getRgb();
            }
        });
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
       switch (position) {
           case 0:
               flag.setCurrentItem(position);
               toolbar.setBackgroundColor(temporary);
               if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) getWindow().setStatusBarColor(temporary);
               toolbar.setVisibility(View.VISIBLE);
               break;
           case 1:
               flag.setCurrentItem(position);
               toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
               if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
               toolbar.setVisibility(View.GONE);
               break;
           case 2:
               flag.setCurrentItem(position);
               toolbar.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
               if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) getWindow().setStatusBarColor(getResources().getColor(R.color.colorPrimaryDark));
               toolbar.setVisibility(View.GONE);
               break;
       }
   }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            if( System.currentTimeMillis() - oldOutTime > 2000){
                oldOutTime = System.currentTimeMillis();
                Toast.makeText(App.getInstance(),"再次点击，退出应用",Toast.LENGTH_SHORT).show();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private ArrayList bannerData(){
        imagesList = new ArrayList<>();
        for(int i:image){
            imagesList.add(i);
        }
        return imagesList;
    }

    private void initFragemnt(){
        mList = new ArrayList<>();
        mBusinessFragment = new BusinessFragment();
        mList.add(mBusinessFragment);
        mShopCarFragment = new ShopCarFragment();
        mList.add(mShopCarFragment);
        mUserFragment = new UserFragment();
        mList.add(mUserFragment);
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(),mList);
        flag.setAdapter(adapter);
        Bundle bundle = new Bundle();
        bundle.putIntegerArrayList("imageList",bannerData());
        mBusinessFragment.setArguments(bundle);
        mBusinessFragment.setListenerRGB(this);
        flag.setOffscreenPageLimit(3);
        flag.setCurrentItem(0);
    }

    private void initFirstToolBarBackgground(){
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),imagesList.get(0));
        Palette.Builder builder = Palette.from(bitmap);
        builder.generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch vibrantSwatch = palette.getVibrantSwatch();
                toolbar.setBackgroundColor(vibrantSwatch.getRgb());
                temporary = vibrantSwatch.getRgb();
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) getWindow().setStatusBarColor(temporary);
            }
        });
    }

    private void initEditextAnimotion(){
        ArrayList<String> strings = new ArrayList<>();
        strings.add("张学友再站歌坛之巅");
        strings.add("Eason歌声依旧完美");
        strings.add("今晚打老虎");
        strings.add("打打代码");
        title.setText(strings.get(strings.size()-1));
        title.setTextList(strings);
        title.startAutoScroll();
    }
}
