package com.example.tanhao.anewbegin.modules.mvp.module.fragmentmodule;

import com.example.tanhao.anewbegin.modules.mvp.bean.NewsSummary;
import com.example.tanhao.anewbegin.network.RequestCallBack;

import java.util.List;

import rx.Subscription;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/28.
 */

public interface BusinessInteractor {
    Subscription getLoadDataType(String url , String id , int startPage , RequestCallBack<List<NewsSummary>> requestCallBack);
}
