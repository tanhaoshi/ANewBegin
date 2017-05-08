package com.example.tanhao.anewbegin.network;

import android.content.Context;

import com.example.tanhao.anewbegin.App;
import com.example.tanhao.anewbegin.Constant;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/30.
 */

public class NetWorkUtils {

    /**
     * http 1.1 三次握手 假设 A 是b/s端(客户)  B是c/s端(服务)
     * a 向 服务端 发送一个syn报文，b接收，会发送给客户端一个 ack的报文 并分配资源
     * a 接收服务端发送的ack报文，并转发给服务端ack报文，并分配资源。
     *
     * http://blog.csdn.net/whuslei/article/details/6667471/ 这是网上理解模式
     * 断开连接 四次挥手过程 断开连接可以是客户端也可以是服务端
     * 假设Client端发起中断连接请求，也就是发送FIN报文。Server端接到FIN报文后，意思是说"我Client端没有数据要发给你了"，
     * 但是如果你还有数据没有发送完成，则不必急着关闭Socket，可以继续发送数据。所以你先发送ACK，"告诉Client端，
     * 你的请求我收到了，但是我还没准备好，请继续你等我的消息"。
     * 这个时候Client端就进入FIN_WAIT状态，继续等待Server端的FIN报文。
     * 当Server端确定数据已发送完成，则向Client端发送FIN报文，"告诉Client端，好了，我这边数据发完了，准备好关闭连接了"。
     * Client端收到FIN报文后，"就知道可以关闭连接了，但是他还是不相信网络，怕Server端不知道要关闭，
     * 所以发送ACK后进入TIME_WAIT状态，如果Server端没有收到ACK则可以重传。“，Server端收到ACK后，"
     * 就知道可以断开连接了"。Client端等待了2MSL后依然没有收到回复，
     * 则证明Server端已正常关闭，那好，我Client端也可以关闭连接了。Ok，TCP连接就这样关闭了！
     *
     * 假设是client端发起中断连接过程，也就是发送fin报文，server端接到fin报文后，意思也就是说客户端没有数据要发送给你了，
     * 但是如果你数据还没有发送完成，不着急关闭socket，可以继续发送数据，所以你先发送ack，告诉client端，
     * 你的请求我收到了，但是还没准备好，请你继续等我的消息
     * 这个时候client端就进入到了fin-wait状态，继续等待着server端的fin报文。
     * 当server端确定数据已经发送完成,则向client端发送fin报文，但是不相信网络，怕server端不知道要关闭了
     * 所以发送time_wait状态，如果server端没有收到ack则可以重传，server端接受到ack后就知道可以断开连接；额
     * client端等待了2msl后依然没有收到回复，则证明server端关闭了，拿好，我client端也可以关闭了连接了。ko，tcp连接就可以关闭了！
     */

    /**
     * 【问题1】为什么连接的时候是三次握手，关闭的时候却是四次握手？
     答：因为当Server端收到Client端的SYN连接请求报文后，可以直接发送SYN+ACK报文。
     其中ACK报文是用来应答的，SYN报文是用来同步的。但是关闭连接时，当Server端收到FIN报文时，
     很可能并不会立即关闭SOCKET，所以只能先回复一个ACK报文，告诉Client端，"你发的FIN报文我收到了"。
     只有等到我Server端所有的报文都发送完了，我才能发送FIN报文，因此不能一起发送。故需要四步握手。
     【问题2】为什么TIME_WAIT状态需要经过2MSL(最大报文段生存时间)才能返回到CLOSE状态？
     答：虽然按道理，四个报文都发送完毕，我们可以直接进入CLOSE状态了，
     但是我们必须假象网络是不可靠的，有可以最后一个ACK丢失。所以TIME_WAIT状态就是用来重发可能丢失的ACK报文。
     */

    /**
     * 因为当server收到client端的syn链接请求报文后，可以直接发送syn+ack报文，
     * 其中ack报文是用来应答的syn报文是用来同步的，但是
     */

    private Context mContext;

    //使用 volatile 避免多线程时 原子的可见性
    private static volatile NetWorkUtils sNetWorkUtils;

    private NetService mNetService;

    private static volatile OkHttpClient sOkHttpClient;

    private Retrofit mRetrofit;

    //使用私有构造方法，避免通过构造方法引用实例
    private NetWorkUtils(Context context , int type){
        this.mContext = context;
                 mRetrofit = new Retrofit.Builder()
                .baseUrl(isType(type))
                .client(NetWorkUtils.getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    private final String isType(int type){
        return type == 1 ? Constant.HOST_NETEASE : Constant.BASE_LIVE_URL ;
    }


    public static NetWorkUtils getInstance(Context context , int type){
        if(sNetWorkUtils == null){
            synchronized(NetWorkUtils.class){
                if(sNetWorkUtils == null){
                    sNetWorkUtils = new NetWorkUtils(App.getInstance(),type);
                }

            }
        }
        if(type == 2){
            sNetWorkUtils = new NetWorkUtils(App.getInstance(),type);
        }
        return sNetWorkUtils;
    }

    public static OkHttpClient getOkHttpClient(){
        if(sOkHttpClient == null){
            synchronized (NetWorkUtils.class){
                if(sOkHttpClient == null){
                    sOkHttpClient = OkHttpUtil.getInstance().getOkHttpClient();
                }
            }
        }
        return sOkHttpClient;
    }

    /**
     * 指定给定线程
     * subscribeOn 与 observeOn 区别
     * subscribeOn()主要改变的是订阅的线程，即call()执行的线程;
       observeOn()主要改变的是发送的线程，即onNext()执行的线程。
       subscribeOn的调用切换之前的线程。
       observeOn的调用切换之后的线程。
       observeOn之后，不可再调用subscribeOn 切换线程
       unsubscribeOn
     * 或许你会问，这有多大的区别吗？的确是有的，比如说产生observable事件是一件费时可能会卡主线程的操作
     * （比如说获取网络数据），那么subscribeOn就是你的选择，这样可以避免卡住主线程。
     *  /**
     * Subscription 与 Subscriber 的区别
     * Subscriber实现了Subscription接口
     * 实现了 56 网络接口对接
     */
    public static Observable.Transformer backTransformer(){
        return new Observable.Transformer() {
            @Override
            public Object call(Object observable) {
                return ((Observable)observable).subscribeOn(Schedulers.io())//生产事件在io
                        .unsubscribeOn(Schedulers.io())//解除Io线程的订阅 避免内存泄露
                        .observeOn(AndroidSchedulers.mainThread())//消费事件在UI线程
                        ;
            }
        };
    }



    //服务接口  我可以想办法把线程封装到此方法来
    /**
     * Create an implementation of the API endpoints defined by the {@code service} interface.
     * @param service 服务接口
     * @return T
     */
    @SuppressWarnings("unchecked") // Single-interface proxy creation guarded by parameter safety.
    public <T> T createService(final Class<T> service){
        return mRetrofit.create(service);
    }

}
