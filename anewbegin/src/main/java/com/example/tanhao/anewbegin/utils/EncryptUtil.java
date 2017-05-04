package com.example.tanhao.anewbegin.utils;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import java.security.MessageDigest;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/6.
 */

//MD5加密工具类
public class EncryptUtil {
    /**
     * 加密
     *
     * @param plaintext 明文
     * @return ciphertext 密文
     */
    public final static String encrypt(String plaintext) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = plaintext.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            return null;
        }
    }

    public static boolean checkAcount(String account, String password, Context context) {
        if (TextUtils.isEmpty(account)) {
            showToast("请填写手机号！",context);
            return false;
        } else if (account.length() != 11) {
            showToast("电话号码格式不正确!",context);
            return false;
        } else if (TextUtils.isEmpty(password)) {
            showToast("请输入密码",context);
            return false;
        } else if (password.length() < 6 || password.length() > 20) {
            showToast("密码格式错误，密码长度最少6位最多20位",context);
            return false;
        } else {
            return true;
        }
    }

    /**
     * 当前提示
     * @param message
     * @param context
     */
    public static void showToast(String message,Context context){
        Toast.makeText(context,message,Toast.LENGTH_SHORT).show();
    }

}
