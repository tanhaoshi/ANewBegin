package com.example.tanhao.anewbegin.modules.mvp.ui.activitys;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.os.PowerManager;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tanhao.anewbegin.App;
import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BaseActivity;
import com.example.tanhao.anewbegin.modules.mvp.bean.LiveDetailBean;
import com.example.tanhao.anewbegin.modules.mvp.presenter.impl.LivePlayPresenterImpl;
import com.example.tanhao.anewbegin.modules.mvp.view.LivePlayView;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LivePlayActivity extends BaseActivity<LivePlayPresenterImpl> implements
        LivePlayView ,PLMediaPlayer.OnPreparedListener,
        PLMediaPlayer.OnVideoSizeChangedListener,
        PLMediaPlayer.OnCompletionListener,
        PLMediaPlayer.OnInfoListener,
        PLMediaPlayer.OnErrorListener{

    @BindView(R.id.surfaceview)
    SurfaceView surfaceView;
    @BindView(R.id.progressbar)
    FrameLayout progressbar;
    @BindView(R.id.tv_roomname_landscape)
    TextView tv_roomname_landscape;
    @BindView(R.id.btn_stream_1080p_landscape)
    Button btn_stream_1080p_landscape;
    @BindView(R.id.btn_stream_360p_landscape)
    Button btn_stream_360p_landscape;
    @BindView(R.id.iv_play_pause_landscape)
    ImageView iv_play_pause_landscape;
    @BindView(R.id.layout_portrait)
    RelativeLayout layout_portrait;

    @Inject
    LivePlayPresenterImpl mLivePlayPresenter;

    private String livetype,liveid,gametype,live_url;
    private PLMediaPlayer mMediaPlayer;
    private boolean isSurfaceViewInit = false;
    private boolean isVideoPrepared = false;
    private boolean isPause = false;
    private int surfacePortraitWidth;
    private int surfacePortraitHeight;
    private List<LiveDetailBean.StreamListBean> streamList = new ArrayList<>();//直播流列表

    private LiveHandler mLiveHandler = new LiveHandler(this);
    private static final int HANDLER_HEADTITLE = 100 ;
    private static final int VALID_TIME = 5 * 1000 ;
    private boolean isControllerHiden = false;
    private boolean isFullscreen = false;   //全屏标志位

    @Override
    protected int getContentView() {
        return R.layout.activity_live_play;
    }

    @Override
    protected void initView() {

        mBasePresenter = mLivePlayPresenter;

        mBasePresenter.onBindView(this);

        loadData(true);
    }

    @Override
    protected void inject() {
        component.inject(this);
    }

    @Override
    protected void initListener() {
         initLive();
    }

    @Override
    public void getLiveDataDetail(LiveDetailBean list) {
        tv_roomname_landscape.setText(list.getLive_title());
        startLiveNew(list);
    }

    @Override
    public void showProgress(boolean isTrue) {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String msg, boolean pullToRefresh) {

    }

    @Override
    public void loadData(boolean pullToRefresh) {
         getTransmissionExtra();
         mLivePlayPresenter.getLiveDetail(livetype ,liveid , gametype);
    }

    private void getTransmissionExtra(){
        Intent intent = getIntent();
        livetype = intent.getStringExtra("livetype");
        liveid = intent.getStringExtra("liveid");
        gametype = intent.getStringExtra("gametype");
    }

    private void initLive(){
        surfaceView.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                progressbar.setVisibility(View.VISIBLE);
                prepareMediaPlayer();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
               //该方法监听页面做出改变
                if(!isSurfaceViewInit){
                   surfacePortraitWidth = width ;
                   surfacePortraitHeight = height ;
                   isSurfaceViewInit = true ;
                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (mMediaPlayer != null) {
                    mMediaPlayer.setDisplay(null);
                }
            }
        });
    }

    private void prepareMediaPlayer(){
        AVOptions avOptions = new AVOptions();
        avOptions.setInteger(AVOptions.KEY_LIVE_STREAMING, 0);  //直播流：1->是 0->否
        avOptions.setInteger(AVOptions.KEY_MEDIACODEC, 0);      //解码类型 1->硬解 0->软解
        avOptions.setInteger(AVOptions.KEY_START_ON_PREPARED, 0);//缓冲结束后自动播放
        avOptions.setInteger(AVOptions.KEY_DELAY_OPTIMIZATION, 1);
        avOptions.setInteger(AVOptions.KEY_PREPARE_TIMEOUT, 10 * 1000);
        avOptions.setInteger(AVOptions.KEY_BUFFER_TIME, 10 * 1000);
        avOptions.setInteger(AVOptions.KEY_GET_AV_FRAME_TIMEOUT, 10 * 1000);
        avOptions.setInteger(AVOptions.KEY_CACHE_BUFFER_DURATION, 10 * 1000);
        avOptions.setInteger(AVOptions.KEY_MAX_CACHE_BUFFER_DURATION, 15 * 1000);

        mMediaPlayer = new PLMediaPlayer(App.getInstance() , avOptions);
        mMediaPlayer.setOnPreparedListener(this);
        mMediaPlayer.setOnVideoSizeChangedListener(this);
        mMediaPlayer.setOnCompletionListener(this);
        mMediaPlayer.setOnInfoListener(this);
        mMediaPlayer.setOnErrorListener(this);

        mMediaPlayer.setWakeMode(getApplicationContext(), PowerManager.PARTIAL_WAKE_LOCK);
        mMediaPlayer.setDisplay(surfaceView.getHolder());

    }

    @Override
    public void onCompletion(PLMediaPlayer plMediaPlayer) {

    }

    @Override
    public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
        Toast.makeText(this,"偷流地址出现错误了!",Toast.LENGTH_SHORT).show();
        progressbar.setVisibility(View.GONE);
//        mLiveHandler.removeMessages(HANDLER_HEADTITLE);
//        mLiveHandler.sendEmptyMessageAtTime(HANDLER_HEADTITLE,VALID_TIME);
        return true;
    }

    @Override
    public boolean onInfo(PLMediaPlayer plMediaPlayer, int what, int i1) {
        //plMediaPlayer 接口首先会进入到这里来缓冲
        switch (what) {
            case PLMediaPlayer.MEDIA_INFO_BUFFERING_START://开始缓冲
//                isVideoPrepared = false;
                Log.i("PLMediaPlayer", "onInfo: MEDIA_INFO_BUFFERING_START");
                break;
            case PLMediaPlayer.MEDIA_INFO_BUFFERING_END://缓冲结束
                Log.i("PLMediaPlayer", "onInfo: MEDIA_INFO_BUFFERING_END");
                break;
            case PLMediaPlayer.MEDIA_INFO_BUFFERING_BYTES_UPDATE:
                Log.i("PLMediaPlayer", "onInfo: MEDIA_INFO_BUFFERING_BYTES_UPDATE");
                break;
            case PLMediaPlayer.MEDIA_INFO_NOT_SEEKABLE:
                Log.i("PLMediaPlayer", "onInfo: MEDIA_INFO_NOT_SEEKABLE");
                break;
            case PLMediaPlayer.MEDIA_INFO_VIDEO_ROTATION_CHANGED:
                Log.i("PLMediaPlayer", "onInfo: MEDIA_INFO_VIDEO_ROTATION_CHANGED");
                break;
            case PLMediaPlayer.MEDIA_INFO_AUDIO_RENDERING_START:
                Log.i("PLMediaPlayer", "onInfo: MEDIA_INFO_AUDIO_RENDERING_START");
                break;
            case PLMediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START://视频缓冲完成可播放
                progressbar.setVisibility(View.GONE);
                isVideoPrepared = true;
                isPause = false;
                iv_play_pause_landscape.setImageResource(isPause ? R.drawable.selector_btn_play : R.drawable.selector_btn_pause);
                Log.d("PLMediaPlayer", "onInfo: MEDIA_INFO_VIDEO_RENDERING_START");
                mLiveHandler.removeMessages(HANDLER_HEADTITLE);
                mLiveHandler.sendEmptyMessageAtTime(HANDLER_HEADTITLE,VALID_TIME);
                break;
            default:
                Log.d("PLMediaPlayer", "onInfo: " + what);
                break;
        }
        return true;
    }

    @Override
    public void onPrepared(PLMediaPlayer plMediaPlayer) {
        progressbar.setVisibility(isVideoPrepared ? View.GONE : View.VISIBLE);
        mMediaPlayer.start();
    }

    @Override
    public void onVideoSizeChanged(PLMediaPlayer plMediaPlayer, int i, int i1, int i2, int i3) {

    }

    private void startLiveNew(LiveDetailBean list){
        try{
            streamList = list.getStream_list();
            LiveDetailBean.StreamListBean stream = streamList.get(streamList.size() - 1);
            live_url = stream.getUrl();
            if (streamList.size() == 1) {
                if (stream.getType().equals("超清")) {
                    btn_stream_360p_landscape.setVisibility(View.GONE);
                }
                if (stream.getType().equals("普清")) {
                    btn_stream_1080p_landscape.setVisibility(View.GONE);
                }
            }
        }catch(Exception e){
            e.printStackTrace();
            live_url = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
        }

        try {
            mMediaPlayer.setDataSource(live_url);//加载直播链接进行播放
            mMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mMediaPlayer != null) {
            mMediaPlayer.pause();
            mMediaPlayer.stop();
            mMediaPlayer.release();
            mMediaPlayer = null;
        }
    }

    private static class LiveHandler extends Handler{

        private final WeakReference<LivePlayActivity> mWeakReference;

        private  LiveHandler(LivePlayActivity livePlayActivity){
             mWeakReference = new WeakReference<LivePlayActivity>(livePlayActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            LivePlayActivity livePlayActivity = mWeakReference.get();
            if(livePlayActivity != null){
                switch (msg.what){
                     case 100:
                         livePlayActivity.layout_portrait.setVisibility(View.GONE);
                         livePlayActivity.isControllerHiden = true;
                        break;
                }
            }
        }
    }

    @OnClick({R.id.surfaceview})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.surfaceview:
                if(isControllerHiden){
                   //当我点击，判断现在是否是隐藏 如果是隐藏的话我就让它出现
                    layout_portrait.setVisibility(View.VISIBLE);
//                    mLiveHandler.removeMessages(HANDLER_HEADTITLE);
//                    mLiveHandler.sendEmptyMessageAtTime(HANDLER_HEADTITLE,VALID_TIME);
                }
                break;
        }
    }
}
