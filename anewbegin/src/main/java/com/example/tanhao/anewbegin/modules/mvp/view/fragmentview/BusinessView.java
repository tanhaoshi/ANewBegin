package com.example.tanhao.anewbegin.modules.mvp.view.fragmentview;

import com.example.tanhao.anewbegin.base.BaseView.BaseView;
import com.example.tanhao.anewbegin.modules.mvp.bean.NewsSummary;

import java.util.List;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/28.
 */

public interface BusinessView extends BaseView{
    void loadSussec(List<NewsSummary> newsSummaries);
}
