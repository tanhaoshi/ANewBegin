package com.example.administrator.myapplication.SwipeMenuListView;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baoyz.swipemenulistview.BaseSwipListAdapter;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.administrator.myapplication.BasePackage.BaseActivity;
import com.example.administrator.myapplication.BeanPackage.Bean;
import com.example.administrator.myapplication.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SwipeMenuActivity extends BaseActivity{

    private SwipeMenuListView mSwipeMenuListView;
    private List<Bean> mList;
    private BswipeMenuAdapter mBswipeMenuAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_swipe_menu);
        mBswipeMenuAdapter = new BswipeMenuAdapter();
        mSwipeMenuListView.setAdapter(mBswipeMenuAdapter);
    }

    @Override
    public void initData() {
        mList = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        for(int i=0;i<20;i++){
            mList.add(new Bean(R.mipmap.ic_launcher,"title"+i,"content"+i,sdf.format(new Date())));
        }
    }
    @Override
    public void initView() {
        mSwipeMenuListView = (SwipeMenuListView)findViewById(R.id.swimpeview);

        SwipeMenuCreator mSwipeMenuCreator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                SwipeMenuItem mOpenItem = new SwipeMenuItem(SwipeMenuActivity.this);
                mOpenItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                          0x3F, 0x25)));
                mOpenItem.setWidth(dp2px(90));
                mOpenItem.setIcon(R.drawable.ic_action_favorite);
                mOpenItem.setTitle("open");
                menu.addMenuItem(mOpenItem);

                SwipeMenuItem mDeleteItem = new SwipeMenuItem(SwipeMenuActivity.this);
                mDeleteItem.setBackground(new ColorDrawable(Color.rgb(0xC9, 0xC9,
                        0xCE)));
                mDeleteItem.setWidth(dp2px(90));
                mDeleteItem.setIcon(R.drawable.ic_action_discard);
                mDeleteItem.setTitle("delete");
                menu.addMenuItem(mDeleteItem);
            }
        };

        mSwipeMenuListView.setMenuCreator(mSwipeMenuCreator);

        mSwipeMenuListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                switch (index) {
                    case 0:
                        mList.remove(position);
                        mBswipeMenuAdapter.notifyDataSetChanged();
                        break;
                    case 1:
                        mList.remove(position);
                        mBswipeMenuAdapter.notifyDataSetChanged();
                        break;
                }
                return true;
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_swipe_menu;
    }

    class BswipeMenuAdapter extends BaseSwipListAdapter{

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Bean getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder mViewHolder = null;
            View view = convertView;
            if(convertView == null){
                    view = getLayoutInflater().inflate(R.layout.swipe_selector,null);
                    mViewHolder = new ViewHolder();
                    mViewHolder.bg_image =(ImageView)view.findViewById(R.id.bg_image);
                    mViewHolder.bg_text = (TextView)view.findViewById(R.id.bg_text);
                    mViewHolder.bg_editext = (TextView)view.findViewById(R.id.bg_editext);
                    mViewHolder.bg_time = (TextView)view.findViewById(R.id.bg_time);
                    view.setTag(mViewHolder);
            }else{
                    mViewHolder = (ViewHolder)view.getTag();
            }
                    mViewHolder.bg_image.setImageResource(R.mipmap.ic_launcher);
                    mViewHolder.bg_text.setText(mList.get(position).getTitle().toString());
                    mViewHolder.bg_editext.setText(mList.get(position).getContent().toString());
                    mViewHolder.bg_time.setText(mList.get(position).getTime().toString());

            return view;
        }

        class ViewHolder{
            ImageView bg_image;
            TextView bg_text,bg_editext,bg_time;
        }
    }

        private int dp2px(int dp) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                    getResources().getDisplayMetrics());
        }
}
