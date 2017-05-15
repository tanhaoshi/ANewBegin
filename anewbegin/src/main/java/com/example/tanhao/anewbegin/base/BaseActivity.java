package com.example.tanhao.anewbegin.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tanhao.anewbegin.R;
import com.example.tanhao.anewbegin.base.BasePresenter.BasePresenter;
import com.example.tanhao.anewbegin.inject.component.ActivityComponent;
import com.example.tanhao.anewbegin.inject.component.DaggerActivityComponent;
import com.example.tanhao.anewbegin.inject.module.ActivityModule;
import com.example.tanhao.anewbegin.layout.TextViewAnimotion;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * @version 1.0
 * @author TanHao
 * Created by tanhao on 2017/2/16.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity{

    /**
     * 由于移动设备一般定义屏幕左上角为坐标原点，向右为x轴增大方向，向下为y轴增大方向，
     * 所以在手机屏幕上的坐标系与数学中常见的坐标系是稍微有点差别的，详情如下：
     * 相对于父控件视角而言
     * getTop();       //获取子View左上角距父View顶部的距离
       getLeft();      //获取子View左上角距父View左侧的距离
       getBottom();    //获取子View右下角距父View顶部的距离
       getRight();     //获取子View右下角距父View左侧的距离
     */


    /**
     * 静态方法块，和静态常量 和静态方法的区别
     *
     当一个类被主动使用时，Java虚拟就会对其初始化，如下六种情况为主动使用：
     当创建某个类的新实例时（如通过new或者反射，克隆，反序列化等）
     当调用某个类的静态方法时
     当使用某个类或接口的静态字段时
     当调用Java API中的某些反射方法时，比如类Class中的方法，或者java.lang.reflect中的类的方法时
     当初始化某个子类时
     当虚拟机启动某个被标明为启动类的类（即包含main方法的那个类）
     Java编译器会收集所有的类变量初始化语句和类型的静态初始化器，将这些放到一个特殊的方法


     static块真正的执行时机。如果了解JVM原理，我们知道，一个类的运行分为以下步骤：

     装载
     连接
     初始化
     其中装载阶段又三个基本动作组成：

     通过类型的完全限定名，产生一个代表该类型的二进制数据流
     解析这个二进制数据流为方法区内的内部数据结
     构创建一个表示该类型的java.lang.Class类的实例
     另外如果一个类装载器在预先装载的时遇到缺失或错误的class文件，它需要等到程序首次主动使用该类时才报告错误。



     连接阶段又分为三部分：

     验证，确认类型符合Java语言的语义，检查各个类之间的二进制兼容性(比如final的类不用拥有子类等)，另外还需要进行符号引用的验证。
     准备，Java虚拟机为类变量分配内存，设置默认初始值。
     解析(可选的) ，在类型的常量池中寻找类，接口，字段和方法的符号引用，把这些符号引用替换成直接引用的过程。

     */

    protected  ActivityComponent component;
    protected Toolbar toolbar;
    protected ImageView iv_back;
    protected TextViewAnimotion title;
    protected ImageView tv_right;
    protected Toolbar baseToolbar;
    protected ImageView base_iv_back;
    protected TextView base_title;
    protected TextView base_tv_right;
    private Unbinder m;
    protected T mBasePresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //The parent class is executed first
        setContentView(getContentView());
        ButterKnife.bind(this);
        initComponent();
        initTooBar();
        initView();
        initListener();
    }

    private void initTooBar() {
        //这里不适合用注解
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        iv_back = (ImageView) findViewById(R.id.iv_back);

        title = (TextViewAnimotion) findViewById(R.id.tv_toolbar_title);
        tv_right = (ImageView) findViewById(R.id.tv_toolbar_right);

        baseToolbar = (Toolbar) findViewById(R.id.base_toolbar);

        base_iv_back = (ImageView) findViewById(R.id.base_iv_back);

        base_title = (TextView) findViewById(R.id.base_tv_toolbar_title);
        base_tv_right = (TextView) findViewById(R.id.base_tv_toolbar_right);

    }

    /**  ==================  所有父类继承的行为  ==================== **/
    protected abstract int getContentView();

    protected abstract void initView();

    protected abstract void inject();

    protected abstract void initListener();

    /**
     * 该方法运行速度比onCreate快
     */
    @Override
    public void onContentChanged() {
        super.onContentChanged();
        m = ButterKnife.bind(this);
    }

    private void initComponent(){
        component = DaggerActivityComponent.builder().activityModule(new ActivityModule(this)).build();
        inject();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mBasePresenter != null){
            mBasePresenter.onDestroy();
            mBasePresenter = null;
        }
        m.unbind();
    }


}
