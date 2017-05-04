package com.example.tanhao.anewbegin.modules.mvp.bean;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/4/1.
 */

public class BusinessBean {


    /**
     * deviceInfo : {"androidRelease":"4.4.4","phoneModel":"CHM-TL00H","sdkVersion":"19","verCode":1,"verName":"1.0.1"}
     * loginName : testtest
     * password : 1qazxsw2
     * platName : test.jngcxh.com
     */

    private String deviceInfo;
    private String loginName;
    private String password;
    private String platName;

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPlatName() {
        return platName;
    }

    public void setPlatName(String platName) {
        this.platName = platName;
    }
}
