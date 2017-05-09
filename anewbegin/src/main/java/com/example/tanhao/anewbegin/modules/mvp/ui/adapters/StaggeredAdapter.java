package com.example.tanhao.anewbegin.modules.mvp.ui.adapters;

import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.tanhao.anewbegin.App;
import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.modules.mvp.bean.NewsSummary;

import java.util.List;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/4/21.
 */

public class StaggeredAdapter extends BaseQuickAdapter<NewsSummary,BaseViewHolder>{


    public StaggeredAdapter(int layoutResId, List<NewsSummary> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, NewsSummary o) {
        baseViewHolder.setText(R.id.staggered_push_newstext,o.getTitle());
        Glide.with(App.getInstance()).load(o.getImgsrc()).crossFade()
                .into((ImageView)baseViewHolder.getView(R.id.staggered_push_news));
    }
}
