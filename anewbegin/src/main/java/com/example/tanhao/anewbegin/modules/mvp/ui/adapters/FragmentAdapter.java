package com.example.tanhao.anewbegin.modules.mvp.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/4/13.
 */

//加载1会加载2，加载2会加载3保留1，加载3，销毁1，加载2，加载1，保留3  FragmentStatePagerAdapter
public class FragmentAdapter extends FragmentPagerAdapter {

    List<Fragment> mList;

    public FragmentAdapter(FragmentManager fragmentManager , List<Fragment> list){
        super(fragmentManager);
        this.mList = list;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }

}
