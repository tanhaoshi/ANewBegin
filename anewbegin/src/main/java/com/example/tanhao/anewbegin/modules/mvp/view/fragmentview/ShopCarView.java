package com.example.tanhao.anewbegin.modules.mvp.view.fragmentview;

import com.example.tanhao.anewbegin.base.BaseView.BaseView;
import com.example.tanhao.anewbegin.modules.mvp.bean.LiveListItemBean;

import java.util.List;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/4/25.
 */

public interface ShopCarView extends BaseView{
    //获取直播源的方法
    void getLiveSource(List<LiveListItemBean> list);
}
