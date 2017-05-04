package com.example.tanhao.anewbegin.utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @version  1.0
 * @author TanHao
 * Created by Administrator on 2017/3/3.
 */

public class ThreadPoolManager {
    /**
     *
     * ThreadPoolExecutor继承了AbstractExecutorService类，并提供了四个构造器，事实上，
     * 通过观察每个构造器的源码具体实现，发现前面三个构造器都是调用的第四个构造器进行的初始化工作。
     下面解释下一下构造器中各个参数的含义：
     corePoolSize：核心池的大小，这个参数跟后面讲述的线程池的实现原理有非常大的关系。
     在创建了线程池后，默认情况下，线程池中并没有任何线程，而是等待有任务到来才创建线程去执行任务，
     除非调用了prestartAllCoreThreads()或者prestartCoreThread()方法，从这2个方法的名字就可以看出，
     是预创建线程的意思，即在没有任务到来之前就创建corePoolSize个线程或者一个线程。默认情况下，
     在创建了线程池后，线程池中的线程数为0，当有任务来之后，就会创建一个线程去执行任务，
     当线程池中的线程数目达到corePoolSize后，就会把到达的任务放到缓存队列当中；
     maximumPoolSize：线程池最大线程数，这个参数也是一个非常重要的参数，
     它表示在线程池中最多能创建多少个线程；
     keepAliveTime：表示线程没有任务执行时最多保持多久时间会终止。默认情况下，
     只有当线程池中的线程数大于corePoolSize时，keepAliveTime才会起作用，
     直到线程池中的线程数不大于corePoolSize，即当线程池中的线程数大于corePoolSize时，
     如果一个线程空闲的时间达到keepAliveTime，则会终止，直到线程池中的线程数不超过corePoolSize。
     但是如果调用了allowCoreThreadTimeOut(boolean)方法，在线程池中的线程数不大于corePoolSize时，
     keepAliveTime参数也会起作用，直到线程池中的线程数为0；
     unit：参数keepAliveTime的时间单位，有7种取值，在TimeUnit类中有7种静态属性：
     */

    private static ThreadPoolManager mThreadPoolManager;

    public ThreadPoolManager(){

    }

    public static ThreadPoolManager getInstance(){
        if(mThreadPoolManager == null){
            return new ThreadPoolManager();
        }else{
            return null;
        }
    }

    //java 四种线程池通过execute 调用
    //1.可缓存的线程池
    private ExecutorService setCacheThreadPool(){
        //创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
        ExecutorService service = Executors.newCachedThreadPool();
        return service;
    }

    //2.创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
    // argms 参数为 最大并发数
    private ExecutorService setFixedThreadPool(int threadSize){
        //创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
        ExecutorService service = Executors.newFixedThreadPool(threadSize);
        return service;
    }

    //3.创建一个定长线程池，支持定时及周期性任务执行
    //该参数为 核心线程个数 还不确定有有多少个线程， 但是最多 拥有coreThreadSize个
    private ExecutorService setScheduledThreadPool(int coreThreadSize){
        ExecutorService service = Executors.newScheduledThreadPool(coreThreadSize);
        return service;
    }

    //4.创建一个单线程化，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)
    private ExecutorService setSingleThreadPool(){
        ExecutorService service = Executors.newSingleThreadExecutor();
        return service;
    }

}
