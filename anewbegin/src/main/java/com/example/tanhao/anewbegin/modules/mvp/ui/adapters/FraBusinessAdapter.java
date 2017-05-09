package com.example.tanhao.anewbegin.modules.mvp.ui.adapters;


import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.modules.mvp.bean.NewsSummary;

import java.util.List;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/4/17.
 */

public class FraBusinessAdapter extends BaseQuickAdapter<NewsSummary.AdsBean,BaseViewHolder> {

    public FraBusinessAdapter(int layoutResId, List<NewsSummary.AdsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsSummary.AdsBean adsBean) {
        Glide.with(mContext).load(adsBean.getImgsrc()).crossFade()
                .into((ImageView)baseViewHolder.getView(R.id.push_news));
        baseViewHolder.setText(R.id.push_newstext,adsBean.getTitle());
    }
}
