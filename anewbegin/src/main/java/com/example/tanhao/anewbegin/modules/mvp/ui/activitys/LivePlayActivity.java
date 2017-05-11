package com.example.tanhao.anewbegin.modules.mvp.ui.activitys;

import android.content.Intent;
import android.os.PowerManager;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.FrameLayout;

import com.example.tanhao.anewbegin.App;
import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BaseActivity;
import com.example.tanhao.anewbegin.modules.mvp.bean.LiveDetailBean;
import com.example.tanhao.anewbegin.modules.mvp.presenter.impl.LivePlayPresenterImpl;
import com.example.tanhao.anewbegin.modules.mvp.view.LivePlayView;
import com.pili.pldroid.player.AVOptions;
import com.pili.pldroid.player.PLMediaPlayer;

import javax.inject.Inject;

import butterknife.BindView;

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

    @Inject
    LivePlayPresenterImpl mLivePlayPresenter;

    private String livetype,liveid,gametype;
    private PLMediaPlayer mMediaPlayer;
    private boolean isSurfaceViewInit = false;

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

    }

    @Override
    public void getLiveDataDetail(LiveDetailBean list) {

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

                }
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

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
        mMediaPlayer.prepareAsync();
    }

    @Override
    public void onCompletion(PLMediaPlayer plMediaPlayer) {

    }

    @Override
    public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
        return false;
    }

    @Override
    public boolean onInfo(PLMediaPlayer plMediaPlayer, int i, int i1) {
        return false;
    }

    @Override
    public void onPrepared(PLMediaPlayer plMediaPlayer) {

    }

    @Override
    public void onVideoSizeChanged(PLMediaPlayer plMediaPlayer, int i, int i1, int i2, int i3) {

    }
}
