package com.example.administrator.myapplication.PhotoPacage;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.myapplication.App;
import com.example.administrator.myapplication.BasePackage.BaseActivity;
import com.example.administrator.myapplication.ListenerPackage.OkhttpclientRequest;
import com.example.administrator.myapplication.MVP.mvp.presenter.JumpViewPresenter;
import com.example.administrator.myapplication.R;
import com.example.administrator.myapplication.Util.ThisUtil;


public class PhotoActivity extends BaseActivity implements View.OnClickListener{

    private TextView http_top,yewu_dan;

    private ImageView iv_top;

    private PhotoWindow mMenuView;

    private static final String TAG = "PhotoActivity";

    private Button btn_id;
    private Uri phtotUri = null;

    private JumpViewPresenter presenter = new JumpViewPresenter(PhotoActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);
        initView();
    }
    @Override
    public void initView() {
        http_top = (TextView)findViewById(R.id.http_top);
        yewu_dan = (TextView)findViewById(R.id.yewu_dan);
        http_top.setClickable(true);
        yewu_dan.setClickable(true);

        iv_top = (ImageView)findViewById(R.id.iv_top);
        iv_top.setClickable(true);

        btn_id = (Button)findViewById(R.id.btn_id);
        btn_id.setOnClickListener(this);

        iv_top.setOnClickListener(this);
        http_top.setOnClickListener(this);
        yewu_dan.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.http_top:
                mMenuView = new PhotoWindow(PhotoActivity.this,mClickListener);
                mMenuView.showAtLocation(PhotoActivity.this.findViewById(R.id.main), Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
                break;
            case R.id.btn_id:
                mOkHttputil.imageTop(App.httpImagePaht,
                        ThisUtil.handleChars(ImageUtil.getRealPathFromUri(PhotoActivity.this,phtotUri)),
                        ImageUtil.getRealPathFromUri(PhotoActivity.this,phtotUri), new OkhttpclientRequest() {
                    @Override
                    public void ResponseSussce(String message) {

                    }
                    @Override
                    public void ResponseFailure(String message) {

                    }
                });
                break;
            case R.id.yewu_dan:
                presenter.jump();
                break;
        }
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_photo;
    }

    private View.OnClickListener mClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           switch (v.getId()){
               //从照相机里面获取
               case R.id.btn_take_photo:
                   getImageFromCreame();
                   break;
               //从相册里面获取
               case R.id.btn_pick_photo:
                   ImageUtil.getImageFromAlbum(PhotoActivity.this);
                   break;
           }
        }
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case App.RESULT_LOCAL:
                phtotUri = data.getData();
                if(phtotUri!=null){
                    iv_top.setImageBitmap(BitmapFactory.decodeFile(ImageUtil.getRealPathFromUri(PhotoActivity.this,phtotUri)));
                    Log.i(TAG,ImageUtil.getRealPathFromUri(PhotoActivity.this, phtotUri));
                }
                break;
            case App.RESULT_REMOTE:
                Uri uri = null;
                if(data!=null && data.getData()!=null){
                    Log.i(TAG,"intent不为空");
                    uri = data.getData();
                }

                if(uri == null){
                    uri = phtotUri;
                    iv_top.setImageBitmap(BitmapFactory.decodeFile(ImageUtil.getRealPathFromUri(PhotoActivity.this,uri)));
                    Log.i(TAG,ImageUtil.getRealPathFromUri(PhotoActivity.this,uri));
                }
                break;
        }
    }

    /**
     * 从照相机里面获取图片
     */
    public void getImageFromCreame(){
        String state = Environment.getExternalStorageState();
        if(Environment.MEDIA_MOUNTED.equals(state)){
            Intent getImageByCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            ContentValues values = new ContentValues();
            values.put(MediaStore.Images.Media.TITLE, ThisUtil.handleTime());
            phtotUri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
            getImageByCamera.putExtra(MediaStore.EXTRA_OUTPUT,phtotUri);
            startActivityForResult(getImageByCamera, App.RESULT_REMOTE);
        }
    }
}