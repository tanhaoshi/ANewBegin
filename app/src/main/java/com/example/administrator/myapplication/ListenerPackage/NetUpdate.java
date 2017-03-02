package com.example.administrator.myapplication.ListenerPackage;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Tan hao shi on 2016/12/7.
 */
public class NetUpdate {

    private OnUpdateListener mOnUpdateListener;

    private static NetUpdate mNetUpdate;

    private  File file = null;

    /**
     * a NetUpdate Example
     * @return
     */
    public static NetUpdate getInstance(){
        if(mNetUpdate!=null){
            return mNetUpdate;
        }else{
            mNetUpdate = new NetUpdate();
            return mNetUpdate;
        }
    }

    public static interface OnUpdateListener{
        void onUpdateProcess(Double process);
        void onUpdateSuccess(int responseCode,String message);
    }

    public void setOnUpdateListener(OnUpdateListener mOnUpdateListener){
        //此方法只为了实例化 实例化onUpdateListener接口
        this.mOnUpdateListener = mOnUpdateListener;
    }

    /**
     * first throght input me SDcard ,course 'Intent' open
     * @param url
     * @return
     */
    public File downFile(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL mURL = new URL(url);
                    HttpURLConnection mHttpURLConnction = (HttpURLConnection)mURL.openConnection();
                    mHttpURLConnction.setRequestMethod("GET");
                    mHttpURLConnction.setConnectTimeout(5000);
                    mHttpURLConnction.setDoInput(true);
                    mHttpURLConnction.setDoOutput(true);
                    FileOutputStream mFileOutputStream = null;
                    InputStream mInputStream = null;
                    int requestCode = mHttpURLConnction.getResponseCode();
                    if(requestCode == 200){
                        mInputStream = mHttpURLConnction.getInputStream();
                        int fileSize = mHttpURLConnction.getContentLength();
                        if(mInputStream!=null){
                            byte[] buffer = new byte[fileSize];
                            int len = 0;
                            int currenlen = 0;
                            if(file != null){
                                mFileOutputStream = new FileOutputStream(filePath(url));
                            }
                            while((len = mInputStream.read(buffer)) != -1){
                                currenlen+=len;
                                if(mFileOutputStream!=null){
                                    mFileOutputStream.write(buffer,0,len);
                                }
                                double process = currenlen/fileSize;
                                mOnUpdateListener.onUpdateProcess(process);
                            }
                            mFileOutputStream.close();
                            mFileOutputStream.flush();
                        }
                        mInputStream.close();
                        mOnUpdateListener.onUpdateSuccess(99," 下载成功!");
                    }else{
                        mOnUpdateListener.onUpdateSuccess(98,"下载失败!");
                    }
                }catch (Exception e){
                    Log.i("NetUpdate:",e.getMessage().toString());
                }
            }
        }).start();
        return file;
    }

    private File filePath(String url){
        String paht = Environment.getExternalStorageState();
        if(paht.equals(Environment.MEDIA_MOUNTED)){
            file = new File(Environment.getExternalStorageDirectory().getAbsolutePath(),getFilePath(url));
        }
        return file;
    }

    private String getFilePath(String url) {
        return url.substring(url.lastIndexOf("/"), url.length());
    }
}
