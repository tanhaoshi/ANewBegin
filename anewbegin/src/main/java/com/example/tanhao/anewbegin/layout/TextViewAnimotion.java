package com.example.tanhao.anewbegin.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * @version 1.0
 * @author TanHao
 * Created by Administrator on 2017/4/14.
 */

public class TextViewAnimotion extends LinearLayout {

    private Context mContext;
    private TextView textViews[] = new TextView[3];

    private LinearLayout llayout;

    private String curText = null;

    /***
     * 动画时间
     */
    private int mAnimTime = 500;

    /**
     * 停留时间
     */
    private int mStillTime = 3500;

    /***
     * 轮播的string
     */
    private List<String> mTextList;

    /***
     * 当前轮播的索引
     */
    private int currentIndex = 0;

    /***
     * 动画模式
     */
    private int animMode = 0;// 默认向上 0--向上，1--向下

    public final static int ANIM_MODE_UP = 0;
    public final static int ANIM_MODE_DOWN = 1;

    private TranslateAnimation animationDown, animationUp;

    public TextViewAnimotion(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initViews();
    }

    private void initViews() {
        llayout = new LinearLayout(mContext);
        llayout.setOrientation(LinearLayout.VERTICAL);
        this.addView(llayout);
        // 0 为向下 抛
        textViews[0] = addText();
        // 初始化字体
        textViews[1] = addText();
        //2为，向上抛
        textViews[2] = addText();
    }

    /***
     * 当界面销毁时
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAutoScroll();// 防止内存泄漏的操作
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //onSizeChanged的启动时间在onDraw之前
        setViewsHeight();
    }

    /***
     * 重新设置VIEW的高度
     */
    private void setViewsHeight() {
        for (TextView tv : textViews) {
            LayoutParams lp = (LayoutParams) tv.getLayoutParams();
            //程序进来的时候，我们并没有给我们的textview设置高度
            //现在我们把三个textview放入容器中
            lp.height = getHeight();
            lp.width = getWidth();
            tv.setLayoutParams(lp);
        }

        /**                    top
         *                      i
         *                      i
         *                      i
         * right   ____________ i_______________  left
         *                      i
         *                      i
         *                      i
         *                    bottom
         *
         *                  setMargins()
         *              android:layout_height="40dp"
         *              40dp 的话，当前高度是为 80
         *                getHeight = 80
         *              当前放入进去的是 3 * getHeight = 240
         *              现在减去一个 80 话， 那也就到 160 的位置了
         *              相对于整个布局的话， 也就处于居中位置
         */
        //我们把容器的高度设置为3个的总和
        LayoutParams lp2 = (LayoutParams) llayout.getLayoutParams();
        lp2.height = getHeight() * (llayout.getChildCount());
        lp2.setMargins(0, -getHeight(), 0, 0);// 使向上偏移一定的高度，用padding,scrollTo都分有问题
        //减去一个的话，现在就还剩下中间位置哪一个
        llayout.setLayoutParams(lp2);
    }

    // /////////////////////以下是一些基本的方法textView要用到///////////////////////////////////

    public void setGravity(int graty) {
        for (TextView tv : textViews) {
            tv.setGravity(graty);
        }
    }

    public void setTextSize(int dpSize) {
        for (TextView tv : textViews) {
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, dpSize);
        }
    }

    public void setTextColor(int color) {
        for (TextView tv : textViews) {
            tv.setTextColor(color);
        }
    }

    private TextView addText() {
        TextView tv = new TextView(mContext);
        //设置文字垂直居中
        tv.setGravity(Gravity.CENTER_VERTICAL);
        llayout.addView(tv);
        return tv;
    }

    /***
     * 设置初始的字
     *
     * @param curText
     */
    public void setText(String curText) {
        this.curText = curText;
        textViews[1].setText(curText);
    }

    /***
     * 开始自动滚动
     */
    public void startAutoScroll() {
        if (mTextList == null || mTextList.size() == 0) {
            //return 将会退出这个方法 不会执行下面的操作了
            return;
        }
        // 先停止
        stopAutoScroll();
        //程序在这经过3秒后 跳转到下面方法去
        this.postDelayed(runnable, mStillTime);// 可用runnable来代替hander或者 timer
    }

    /***
     * 停止自动滚动
     */
    public void stopAutoScroll() {
        this.removeCallbacks(runnable);
    }

    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            // 0 % 4 = 0 , 1 % 4 = 1 , 2 % 4 = 2 , 3 % 4 = 3, 4 % 4 =0 , 5 % 4 = 1 , 6 % 4 = 2 , 7 % 4 = 3 , 8 % 4 = 0 ,9 % 4 =1;
            currentIndex = (currentIndex) % mTextList.size();
            switch (animMode) {
                case ANIM_MODE_UP:
                    setTextUpAnim(mTextList.get(currentIndex));
                    break;
                case ANIM_MODE_DOWN:
                    setTextDownAnim(mTextList.get(currentIndex));
                    break;
            }
            currentIndex++;
            //当我们在这里再点击一下后变成了一个死循环了， 如果没这句话的话，不会执行死循环+ mAnimTime
            //当这里经过3秒后 又继续下一步操作
            TextViewAnimotion.this.postDelayed(runnable, mStillTime);
        }
    };

    /***
     * 向上弹动画
     *
     * @param //curText
     */
    public void setTextUpAnim(String text) {
        this.curText = text;
        //首先setText ,然后 向上抛 // 这里有一个三秒的过程
        textViews[2].setText(text);
        up();// 向上的动画
    }

    public void setTextDownAnim(String text) {
        this.curText = text;
        textViews[0].setText(text);
        down();// 向下的动画
    }

    public void setDuring(int during) {
        this.mAnimTime = during;
    }

    /***
     * 向上动画
     */
    private void up() {
        //清楚动画
        llayout.clearAnimation();
        if (animationUp == null)
            animationUp = new TranslateAnimation(0, 0, 0, -getHeight());
        animationUp.setDuration(mAnimTime);
        llayout.startAnimation(animationUp);
        animationUp.setAnimationListener(listener);
    }

    /***
     * 向下动画
     */
    public void down() {
        llayout.clearAnimation();
        if (animationDown == null)
            animationDown = new TranslateAnimation(0, 0, 0, getHeight());
        animationDown.setDuration(mAnimTime);
        llayout.startAnimation(animationDown);
        //完成上面动画后，我们要监听动画完成，完成后我们继续下一步设置text
        animationDown.setAnimationListener(listener);
    }

    /***
     * 动画监听，动画完成后，动画恢复，设置文本
     */
    private Animation.AnimationListener listener = new Animation.AnimationListener() {

        @Override
        public void onAnimationStart(Animation arg0) {
        }

        @Override
        public void onAnimationRepeat(Animation arg0) {
        }

        @Override
        public void onAnimationEnd(Animation arg0) {
            //当我们向上抛出一个text的时候，也就是说我们上一个文字走过后，程序走这里来，然后我们setText下
            setText(curText);
        }
    };

    public int getAnimTime() {
        return mAnimTime;
    }

    public void setAnimTime(int mAnimTime) {
        this.mAnimTime = mAnimTime;
    }

    public int getStillTime() {
        return mStillTime;
    }

    public void setStillTime(int mStillTime) {
        this.mStillTime = mStillTime;
    }

    public List<String> getTextList() {
        return mTextList;
    }

    public void setTextList(List<String> mTextList) {
        this.mTextList = mTextList;
    }

    public int getCurrentIndex() {
        return currentIndex;
    }

    public void setCurrentIndex(int currentIndex) {
        this.currentIndex = currentIndex;
    }

    public int getAnimMode() {
        return animMode;
    }

    public void setAnimMode(int animMode) {
        this.animMode = animMode;
    }

}
