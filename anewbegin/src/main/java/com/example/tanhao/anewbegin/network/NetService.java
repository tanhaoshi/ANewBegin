package com.example.tanhao.anewbegin.network;

import com.example.tanhao.anewbegin.modules.mvp.bean.LiveBaseBean;
import com.example.tanhao.anewbegin.modules.mvp.bean.LiveListItemBean;
import com.example.tanhao.anewbegin.modules.mvp.bean.NewsSummary;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/30.
 */

public interface NetService {
    /**
     *  FormUrlEncoded注解:
     用于修饰Field注解和FieldMap注解
     使用该注解,表示请求正文将使用表单网址编码。字段应该声明为参数，并用@Field注释或FieldMap注释。
     使用FormUrlEncoded注解的请求将具
     ”application / x-www-form-urlencoded”
     MIME类型。字段名称和值将先进行UTF-8进行编码,再根据RFC-3986进行URI编码.
     **/

    /**
     * QueryMap注解:
     作用于方法的参数
     以map的形式添加查询参数,即请求参数
     参数的键和值都通过String.valueOf()转换为String格式
     map的键和值默认进行URL编码
     map中每一项的键和值都不能为空,否则抛出IllegalArgumentException异常
     * @param url
     * @return
     */
    //不用完全转换为 string 可以支持自己的类型
    @GET("{url}")
    @FormUrlEncoded
    Observable<ResponseBody> executeAllGet(
            @Path("url") String url,
            @FieldMap Map<String,Object> maps
    );

    @GET("{url}")
    @FormUrlEncoded
    Observable<ResponseBody> executeStrGet(
            @Path("url") String url,
            @QueryMap Map<String,String> maps
    );

    /**
     * Http定义了与服务器交互的不同方法，最基本的方法有4种，分别是GET，POST，PUT，DELETE。
     * URL全称是资源描述符，我们可以这样认为：一个URL地址，它用于描述一个网络上的资源，
     * 而HTTP中的GET，POST，PUT，DELETE就对应着对这个资源的查，改，增，删4个操作。到这里，大家应该有个大概的了解了，
     * GET一般用于获取/查询资源信息，而POST一般用于更新资源信息。
     * @param cacheControl
     * @param type
     * @param id
     * @param startPage
     * @return
     */
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Observable<Map<String ,List<NewsSummary>>> getNewLists(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type,
            @Path("id") String id,
            @Path("startPage") int startPage);


    //完全转为 支持各种类型
    @POST
    @FormUrlEncoded
    Observable<ResponseBody> executePostFrom(
         @Url String url,
         @FieldMap Map<String,Object> map
    );

    /**
     *   Call<ResponseBody> sendNormal(@Body Repo repo)
     *    @Body用来 传送实体类
     *
     *    @表单上传 即 post请求 必须使用 @Field 开头
     *    @Field必须如此上传
     *    //Call<ResponseBody> example(@Field("name") String name,@Field("occupation") String occupation);
     *    @Field 必须和 @FormUrlEncoded联合使用
     */
    //这个支持集合里面支持string
    @POST
    @FormUrlEncoded
        Observable<ResponseBody> executePost(
            @Url String url,
            @Field("postdata") String message
            );


    //下载单个文件
    @Streaming
    @GET
    Observable<ResponseBody> downloadFile(
            @Url String url
    );

    //下载单文件
    @Multipart
    @POST
    Observable<ResponseBody> unloadFile(
            @Url String url,
            @Part MultipartBody.Part filePart
            );

    //多文件下载
    @Multipart
    @POST
    Observable<ResponseBody> uploadFiles(
            @Url String url,
            @PartMap Map<String, RequestBody> maps
    );

    //请求获取不同游戏的直播列表
    @GET("/api/live/list/")
    Observable<LiveBaseBean<List<LiveListItemBean>>> getLiveList(
            @Query("offset") int offset,
            @Query("limit") int limit,
            @Query("live_type") String live_type,
            @Query("game_type") String game_type
    );
}
