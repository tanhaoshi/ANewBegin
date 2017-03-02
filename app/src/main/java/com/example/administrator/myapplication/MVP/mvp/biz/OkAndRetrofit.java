package com.example.administrator.myapplication.MVP.mvp.biz;

import com.example.administrator.myapplication.MVP.mvp.bean.User;

import java.util.ArrayList;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by tanhaoshi on 2017/2/9.
 */
public interface OkAndRetrofit {

    @GET("users")
    Call<ArrayList<String>> getUsers();

    @POST()
    @FormUrlEncoded
    Observable<User> toLogin(
            @Url() String url,
            @FieldMap Map<String, String> params);
}
