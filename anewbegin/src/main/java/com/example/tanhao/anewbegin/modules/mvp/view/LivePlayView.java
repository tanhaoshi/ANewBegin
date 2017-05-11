package com.example.tanhao.anewbegin.modules.mvp.view;

import com.example.tanhao.anewbegin.base.BaseView.BaseView;
import com.example.tanhao.anewbegin.modules.mvp.bean.LiveDetailBean;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/5/10.
 */

public interface LivePlayView extends BaseView{
    void getLiveDataDetail(LiveDetailBean list);
}
