package com.example.tanhao.anewbegin.modules.mvp.ui.adapters;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tanhao.anewbegin.App;
import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.layout.CropCircleTransformation;
import com.example.tanhao.anewbegin.modules.mvp.bean.LiveListItemBean;

import java.util.List;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/5/8.
 */

public class ShopCarListAdapter extends BaseQuickAdapter<LiveListItemBean,BaseViewHolder> {

    public ShopCarListAdapter(List<LiveListItemBean> data){
        super(R.layout.fragment_adapter_shop,data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, LiveListItemBean liveListItemBean) {
        baseViewHolder.setText(R.id.live_name , liveListItemBean.getLive_title().toString())
                .setText(R.id.live_talking ,liveListItemBean.getLive_nickname())
                .setText(R.id.live_user ,String.valueOf(liveListItemBean.getLive_online()))
                .addOnClickListener(R.id.live_image)
                ;
        Glide.with(App.getInstance())
                .load(liveListItemBean.getLive_img())
                .crossFade()
                .centerCrop()
                .into((ImageView)baseViewHolder.getView(R.id.live_image));

        Glide.with(App.getInstance())
                .load(liveListItemBean.getLive_userimg())
                .placeholder(R.drawable.ic_avatar_default)
                .bitmapTransform(new CropCircleTransformation(App.getInstance()))
                .into((ImageView)baseViewHolder.getView(R.id.live_head));
    }
}
