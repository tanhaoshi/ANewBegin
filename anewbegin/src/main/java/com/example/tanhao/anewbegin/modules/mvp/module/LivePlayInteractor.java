package com.example.tanhao.anewbegin.modules.mvp.module;

import com.example.tanhao.anewbegin.modules.mvp.bean.LiveDetailBean;
import com.example.tanhao.anewbegin.network.RequestCallBack;

import rx.Subscription;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/5/10.
 */

public interface LivePlayInteractor {
    Subscription getLiveStream(String live_type, String live_id, String game_type , RequestCallBack<LiveDetailBean> requestCallBack);
}
