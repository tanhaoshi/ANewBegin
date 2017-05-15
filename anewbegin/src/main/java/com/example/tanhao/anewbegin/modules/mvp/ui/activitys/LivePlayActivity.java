package com.example.tanhao.anewbegin.modules.mvp.ui.activitys;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BaseActivity;
import com.example.tanhao.anewbegin.modules.mvp.bean.LiveDetailBean;
import com.example.tanhao.anewbegin.modules.mvp.presenter.impl.LivePlayPresenterImpl;
import com.example.tanhao.anewbegin.modules.mvp.view.LivePlayView;
import com.pili.pldroid.player.PLMediaPlayer;
import com.pili.pldroid.player.widget.PLVideoTextureView;
import com.socks.library.KLog;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class LivePlayActivity extends BaseActivity<LivePlayPresenterImpl> implements
        LivePlayView{

    @BindView(R.id.surfaceview)
    PLVideoTextureView surfaceView;
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
    @BindView(R.id.layout_top)
    FrameLayout layout_top;
    @BindView(R.id.layout_landscape)
    RelativeLayout layout_landscape;

    @Inject
    LivePlayPresenterImpl mLivePlayPresenter;

    private String livetype,liveid,gametype,live_url;
    private List<LiveDetailBean.StreamListBean> streamList = new ArrayList<>();//直播流列表

    private static final int HANDLER_HEADTITLE = 100 ;
    private static final int VALID_TIME = 5 * 1000 ;
    private boolean isControllerHiden = false;

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
        tv_roomname_landscape.setText(list.getLive_title());
        LiveDetailBean.StreamListBean stream = streamList.get(streamList.size() - 1);
        live_url = stream.getUrl();
        initLive(live_url);
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

    private void initLive(final String url){
        surfaceView.setVideoPath(url);
        surfaceView.setOnPreparedListener(new PLMediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(PLMediaPlayer plMediaPlayer) {
                KLog.i("onPrepared:" + url);
                start();
            }
        });

        surfaceView.setOnBufferingUpdateListener(new PLMediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(PLMediaPlayer plMediaPlayer, int i) {
                if(i>0)
                    KLog.i("onBufferingUpdate|" + i);
            }
        });

        surfaceView.setOnCompletionListener(new PLMediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(PLMediaPlayer plMediaPlayer) {
                KLog.i("onCompletion");
            }
        });
        surfaceView.setOnInfoListener(new PLMediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(PLMediaPlayer plMediaPlayer, int i, int i1) {
                KLog.i("onInfo|i:" + i + "--i1:" + i1);
                return false;
            }
        });

        surfaceView.setOnErrorListener(new PLMediaPlayer.OnErrorListener() {
            @Override
            public boolean onError(PLMediaPlayer plMediaPlayer, int i) {
                KLog.i("onError:i:" + i);
                return false;
            }
        });
    }

    public boolean isPlaying(){
        return surfaceView.isPlaying();
    }

    private void start(){
        if(surfaceView!=null)
            surfaceView.start();
    }

    private void pause(){
        if(surfaceView!=null)
            surfaceView.pause();
    }

    private void stopPlayback(){
        if(surfaceView!=null)
            surfaceView.stopPlayback();
    }

    private void seekTo(long i){
        surfaceView.seekTo(i);
    }

    @Override
    protected void onResume() {
        super.onResume();
        start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopPlayback();
    }

    private LiveHandler mLiveHandler = new LiveHandler(this);

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

    @OnClick({R.id.surfaceview,
              R.id.iv_fullscreen_portrait})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.surfaceview:
                if(isControllerHiden){
                    layout_portrait.setVisibility(View.VISIBLE);
                    mLiveHandler.removeMessages(HANDLER_HEADTITLE);
                    mLiveHandler.sendEmptyMessageDelayed(HANDLER_HEADTITLE , VALID_TIME);
                }
                break;
            case R.id.iv_fullscreen_portrait:
                //增加这个标签
                clickFullScreen();
                break;
        }
    }

    public boolean isLandscape(){
        return getRequestedOrientation() == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
    }

    public void clickFullScreen(){
        if(isLandscape()){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }else{
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }
}
