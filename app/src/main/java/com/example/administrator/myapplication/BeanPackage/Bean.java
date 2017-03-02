package com.example.administrator.myapplication.BeanPackage;

/**
 * Created by tanhaoshi on 2016/12/8.
 */
public class Bean {

    int image;

    String title;

    String content;

    String time;

    public Bean(int image,String title,String content,String time){
        this.image = image;
        this.title = title;
        this.content = content;
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
