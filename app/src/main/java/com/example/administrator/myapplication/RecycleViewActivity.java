package com.example.administrator.myapplication;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.myapplication.BasePackage.BaseActivity;
import com.example.administrator.myapplication.MVP.mvp.presenter.LoadDataPresenter;
import com.example.administrator.myapplication.MVP.mvp.view.ILoaddataView;
import com.example.administrator.myapplication.Util.DriverItemDecoration;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RecycleViewActivity extends BaseActivity implements ILoaddataView {

    @BindView(R.id.id_recyclerview)
    RecyclerView id_recyclerview;
    private ArrayList<String> arrayList = new ArrayList<String>();
    private LoadDataPresenter presenter = new LoadDataPresenter(arrayList, this);
    private HomeAdapter homeAdapter;

    /**
     * 假设让我设计一个社交网络APP，我该如何设计它？
     * 设计的话，我们肯定要根据业务来设计我们的APP 考虑多个方面，程序逻辑合理性能极佳后期可维护性极高，用户的方便，
     * 我们要考虑的前期工作，我将要使用到的父类，我要如何使用java面向对象的思想来设计我的程序。
     * 这里回想java面向对象的5大特征，继承，
     */
    @BindView(R.id.search_bar)
    LinearLayout search_bar;

    @BindView(R.id.searh_editext)
    EditText searh_editext;

    public enum ITEM_TYPE {
        ITEM1,
        ITEM2
    }

    private Animation myAnimation_Translate;

    public static final String TAG = "RecycleViewActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_recycle_view);
        ButterKnife.bind(this);
        initView();
        initData();
    }

    public void initView() {
//        id_recyclerview = (RecyclerView) findViewById(R.id.id_recyclerview);
//        search_bar = (LinearLayout) findViewById(R.id.search_bar);
//        searh_editext = (EditText) findViewById(R.id.searh_editext);
        id_recyclerview.setLayoutManager(new LinearLayoutManager(this));
        id_recyclerview.addItemDecoration(new DriverItemDecoration(RecycleViewActivity.this, DriverItemDecoration.VERTICAL_LIST));
        //网格式布局所用到的  垂直
        //id_recyclerview.setLayoutManager(new GridLayoutManager(RecycleViewActivity.this,4));
        //id_recyclerview.setLayoutManager(new StaggeredGridLayoutManager(4,StaggeredGridLayoutManager.HORIZONTAL));
        //问题出现 高度有问题
        //id_recyclerview.addItemDecoration(new DividerGridItemDecoration(RecycleViewActivity.this));

        //arrayList = new ArrayList<>();
        //presenter = new LoadDataPresenter(arrayList,this);

        id_recyclerview.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(getScollYDistance()>120){
                    //search_bar.setVisibility(View.VISIBLE);
                    //animationChange();
                }else{
                    //search_bar.setVisibility(View.GONE);
                }
//                Log.i(TAG, "屏幕整体高度:" + ThisUtil.getWindowHeight(RecycleViewActivity.this));
//                Log.i(TAG, "第一个可见子视图:" + getScollYDistance());
//                Log.i(TAG, "滑动的距离:" + (-dy));
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_recycle_view;
    }

    public void initData() {
        presenter.loadData();
        id_recyclerview.setAdapter(homeAdapter = new HomeAdapter());
    }

    @Override
    public void displayView(ArrayList<String> arrayList) {

    }

    @Override
    public void showMessage(String message) {
        App.showToast(RecycleViewActivity.this, message);
    }

    class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @Override
        public int getItemViewType(int position) {
            //不是简单的添加头部与尾部，它是直接替代一项.
            //比如我这里是当position == 0的时候,我的styleviewholder将替代viewholder中的第一项
            return position == 0 ? ITEM_TYPE.ITEM2.ordinal() : ITEM_TYPE.ITEM1.ordinal();
        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (viewType == ITEM_TYPE.ITEM1.ordinal()) {
                HomeViewHolder homeViewHolder = new HomeViewHolder(LayoutInflater.from(RecycleViewActivity.this)
                        .inflate(R.layout.layout_recyclerview, parent, false));
                return homeViewHolder;
            } else if (viewType == ITEM_TYPE.ITEM2.ordinal()) {
                StyleViewHolder styleViewHolder = new StyleViewHolder(LayoutInflater.from(RecycleViewActivity.this)
                        .inflate(R.layout.recyclerviewhead_layout, parent, false));
                return styleViewHolder;
            }
            return null;
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
            if (holder instanceof HomeViewHolder) {
                ((HomeViewHolder) holder).tv.setText(arrayList.get(position));
            } else if (holder instanceof StyleViewHolder) {

            }
        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        public class HomeViewHolder extends RecyclerView.ViewHolder {

            TextView tv;

            public HomeViewHolder(View itemView) {
                super(itemView);
                tv = (TextView) itemView.findViewById(R.id.text_writer);
            }
        }

        public class StyleViewHolder extends RecyclerView.ViewHolder {

            public StyleViewHolder(View styleView) {
                super(styleView);
            }
        }
    }

    public int getScollYDistance() {
        LinearLayoutManager layoutManager = (LinearLayoutManager) id_recyclerview.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        return (position) * itemHeight - firstVisiableChildView.getTop();
    }

//    /**
//     * 动画
//     */
//     public void animationChange(){
//         myAnimation_Translate = new TranslateAnimation(500f,0f,0f,0f);
//         myAnimation_Translate.setDuration(200);
//         myAnimation_Translate.setFillAfter(true);
//         searh_editext.setAnimation(myAnimation_Translate);
//     }
}