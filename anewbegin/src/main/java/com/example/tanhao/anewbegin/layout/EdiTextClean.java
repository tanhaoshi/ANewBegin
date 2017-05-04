package com.example.tanhao.anewbegin.layout;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/3/2.
 */

public class EdiTextClean extends EditText{

    private Drawable mDrawable;

    boolean isTrue;

    public EdiTextClean(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init(){
        //this method return a drawble througth
        Drawable[] drawables = this.getCompoundDrawables();

        //根据看它的底层实现方法，第三个参数是返回右边图片
        mDrawable = drawables[2];

        //首先我们给监听焦点 因为刚开始的时候我们是没有输入东西的 所以刚开始的时候应该将它隐藏

        this.setOnFocusChangeListener(new FocuseChangeImpl());

        //得到图片后，我们就应该去监听该图片什么时候该出来，什么时候不该出来。

        this.addTextChangedListener(new TextChangeImpl());

        //初始化之前我们将其设置为隐藏
        setClearDrawableVisible(false);
    }

    private class FocuseChangeImpl implements OnFocusChangeListener{

        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            //获得焦点
            //当我们获得焦点 接口就进来了  hasFocus为true
            isTrue = hasFocus;
            if (isTrue) {
                boolean isVisible = getText().toString().length() >= 1;
                setClearDrawableVisible(isVisible);
            } else {
                setClearDrawableVisible(false);
            }
        }
    }

     private class TextChangeImpl implements TextWatcher{

         @Override
         public void beforeTextChanged(CharSequence s, int start, int count, int after) {

         }
         @Override
         public void onTextChanged(CharSequence s, int start, int before, int count) {

         }
         @Override
         public void afterTextChanged(Editable s) {
             boolean isVisible = getText().toString().length() >= 1;
             setClearDrawableVisible(isVisible);
         }

     }

    //清除editext文字按钮,要怎么才能清除  点击我们的按钮的 给它设置监听事件，这样我们就可以清楚它了。
    //我们要根据什么条件来监听 根据我们按下去(相对于控件来说) 肯定我们的点下去的距离要大于图片边缘
    //以左的距离，小于图片右边缘以外的距离，我们就可以清楚我们的文字了。
    /**
     * 当手指抬起的位置在clean的图标的区域 我们将此视为进行清除操作
     * getWidth():得到控件的宽度
     * event.getX():抬起时的坐标(改坐标是相对于控件本身而言的)
     * getTotalPaddingRight():clean的图标左边缘至控件右边缘的距离
     * 返回图片左边以前的距离
     * getPaddingRight():clean的图标右边缘至控件右边缘的距离
     * 于是:
     * getWidth() - getTotalPaddingRight()表示: 控件左边到clean的图标左边缘的区域
     * getWidth() - getPaddingRight()表示: 控件左边到clean的图标右边缘的区域 所以这两者之间的区域刚好是clean的图标的区域
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            //当它向下按下时候
            case MotionEvent.ACTION_UP:
                if((event.getX() > (getWidth()-getTotalPaddingRight()))&& (event.getX() < (getWidth()-getPaddingRight()))){
                    setText("");
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    private void setClearDrawableVisible(boolean isVisible){

        Drawable curentDrawble;

        if(isVisible){
            curentDrawble = mDrawable;
        }else{
            curentDrawble = null;
        }

        setCompoundDrawables(getCompoundDrawables()[0],getCompoundDrawables()[1],
                curentDrawble,getCompoundDrawables()[3]);
    }
}
