package com.example.administrator.myapplication.MVP.mvp.presenter;

import com.example.administrator.myapplication.MVP.mvp.biz.ILoadData;
import com.example.administrator.myapplication.MVP.mvp.biz.LoadData;
import com.example.administrator.myapplication.MVP.mvp.biz.RequestData;
import com.example.administrator.myapplication.MVP.mvp.view.ILoaddataView;

import java.util.ArrayList;

import hugo.weaving.DebugLog;

/**
 * Created by tanhaoshi on 2017/1/12.
 */
public class LoadDataPresenter {

    private ILoadData data;
    private ILoaddataView loaddataView;

    public LoadDataPresenter(ArrayList<String> arrayList,ILoaddataView loaddataView){
        data = new LoadData(arrayList);
        this.loaddataView = loaddataView;
    }

    @DebugLog
    public void loadData(){
        data.load(new RequestData() {
            @Override
            public void success(ArrayList<String> arrayList) {
                 loaddataView.displayView(arrayList);
            }

            @Override
            public void faild() {

            }
        });
    }
}
