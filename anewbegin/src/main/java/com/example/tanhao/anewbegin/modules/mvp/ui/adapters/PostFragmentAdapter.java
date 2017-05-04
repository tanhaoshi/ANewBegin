package com.example.tanhao.anewbegin.modules.mvp.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/4/28.
 */

public class PostFragmentAdapter extends FragmentPagerAdapter{

    List<Fragment> mList;
    List<String> mStringsList;

    public PostFragmentAdapter(FragmentManager fragmentManager , List<Fragment> list , List<String> stringList){
        super(fragmentManager);
        this.mList = list;
        this.mStringsList = stringList;
    }

    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList != null ? mList.size() : 0 ;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mStringsList.get(position % mStringsList.size());
    }
}
