package com.example.administrator.myapplication.PhotoPacage;


import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;

import com.example.administrator.myapplication.R;

public class PhotoWindow extends PopupWindow {

    private Button btn_take_photo,btn_pick_photo,btn_cancel;

    private View mMenuView;

    public PhotoWindow(Activity mcontext, View.OnClickListener mOnClick){
        super(mcontext);
        LayoutInflater inflater = (LayoutInflater)mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mMenuView = inflater.inflate(R.layout.popubview,null);
        btn_take_photo = (Button)mMenuView.findViewById(R.id.btn_take_photo);
        btn_pick_photo = (Button)mMenuView.findViewById(R.id.btn_pick_photo);
        btn_cancel = (Button)mMenuView.findViewById(R.id.btn_cancel);

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btn_pick_photo.setOnClickListener(mOnClick);
        btn_take_photo.setOnClickListener(mOnClick);
        this.setContentView(mMenuView);
        this.setWidth(WindowManager.LayoutParams.FILL_PARENT);
        this.setHeight(WindowManager.LayoutParams.WRAP_CONTENT);
        this.setFocusable(true);
        this.setAnimationStyle(R.style.take_photo_anim);

        ColorDrawable cd = new ColorDrawable(0xb0000000);
        this.setBackgroundDrawable(cd);

        mMenuView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int height = mMenuView.findViewById(R.id.pop_layout).getTop();
                int y=(int) event.getY();
                if(event.getAction() == MotionEvent.ACTION_UP){
                    if(y<height){
                        dismiss();
                    }
                }
                    return true;
            }
        });
    }
}
