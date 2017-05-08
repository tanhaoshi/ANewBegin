package com.example.tanhao.anewbegin.modules.mvp.module.fragmentmodule;

import com.example.tanhao.anewbegin.modules.mvp.bean.LiveListItemBean;
import com.example.tanhao.anewbegin.network.RequestCallBack;

import java.util.List;

import rx.Subscription;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/4/25.
 */

public interface ShopCarInteractor {
    Subscription loadLiveSourceList(int offset, int limit, String live_type, String game_type, RequestCallBack<List<LiveListItemBean>> requestCallBack);
}
