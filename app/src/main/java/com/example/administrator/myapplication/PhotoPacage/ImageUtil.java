package com.example.administrator.myapplication.PhotoPacage;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;

import com.example.administrator.myapplication.App;

/**
 * Created by Administrator on 2016/12/19.
 */
public class ImageUtil {

    /**
     * 从照相机里面获取图片
     */


    /**
     * 从本地获取相片
     */
    public static void getImageFromAlbum(Activity context){
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        context.startActivityForResult(intent, App.RESULT_LOCAL);
    }

    public static void cropImageUtil(Uri uri,int outPutX,int outPutY,int requestCode){
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        //intent.putExtra("")
    }

    /**
     * 找到图片存储的地址
     */
    public static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = { MediaStore.Images.Media.DATA };
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
