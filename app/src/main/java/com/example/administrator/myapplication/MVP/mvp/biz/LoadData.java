package com.example.administrator.myapplication.MVP.mvp.biz;


import java.util.ArrayList;

/**
 * Created by tanhaoshi on 2017/1/12.
 */
public class LoadData implements ILoadData {

    private ArrayList<String> arrayList;

    public LoadData(ArrayList<String> arrayList){
       this.arrayList = arrayList;
    }

    @Override
    public void load(final RequestData data) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i= 'A'; i<'Z'; i++){
                     arrayList.add((char)i+"");
                }
                data.success(arrayList);
            }

        }).start();
    }
}
