package com.example.tanhao.anewbegin.modules.mvp.presenter.fragmentpresenter;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/5/5.
 */

public interface ShopCarListPresenter{
    void loadLiveSource(int offset, int limit, String live_type, String game_type);
}
