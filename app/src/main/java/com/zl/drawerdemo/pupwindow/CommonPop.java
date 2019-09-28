package com.zl.drawerdemo.pupwindow;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;

import com.zl.drawerdemo.R;


/**
 * 自定义公共PopupWindow，便于控制随时随地的生成弹窗，控制大小和点击事件等（测试版，功能不全）
 */
public class CommonPop extends PopupWindow {

    /**
     * 设为false可以控制dismiss()无参方法不调用
     * 即控制点击PopupWindow外部不消失
     */
    private boolean canDismiss = true;

    private View pop_close_btn;
    private final Context mContext;
    private View poplayout;


    public CommonPop(Context context, @LayoutRes int resource, ViewGroup parent, boolean attachToRoot) {
        mContext = context.getApplicationContext();

        this.poplayout = LayoutInflater.from(mContext).inflate(resource,parent,attachToRoot);

        InitDefaultPop();
    }


    private void InitDefaultPop() {

        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.poplayout);
        // 设置弹出窗体的宽和高
        this.setHeight(LinearLayout.LayoutParams.WRAP_CONTENT);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为白色
        ColorDrawable dw = new ColorDrawable(Color.WHITE);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画
        this.setAnimationStyle(R.style.test_pop_anim);

    }




    /**
     * 通过ID设置弹窗关闭按钮（所以限定了这个id必须是弹窗布局里面的才行）
     * 如需自定义关闭按钮点击事件，可调用SetCloseEvent方法
     */
    public void SetCloseBtn(@IdRes int IdRes) {
        pop_close_btn = this.poplayout.findViewById(IdRes);
        SetCloseEvent();
    }

    /**
     * 通过View直接设置弹窗关闭按钮（所以可以设置弹窗外其他布局上的view来控制弹窗开关）
     */
    public void SetCloseBtn(View btn_close) {
        pop_close_btn = btn_close;
        SetCloseEvent();
    }

    private void SetCloseEvent() {
        pop_close_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    /**
     * 自定义关闭按钮点击事件
     *
     * @param OnClickListener
     */
    public void SetCloseEvent(View.OnClickListener OnClickListener) {
        pop_close_btn.setOnClickListener(OnClickListener);
    }


    /**
     * 添加OnTouchListener监听
     *
     * @param onTouchListener
     */
    public void SetOnTouchListener(View.OnTouchListener onTouchListener) {
        this.poplayout.setOnTouchListener(onTouchListener);
    }


    @Override
    public void dismiss() {
        if(canDismiss)
            finanldismiss();
        else {
//            StackTraceElement[] stackTrace = new Exception().getStackTrace();
//            if(stackTrace.length >= 2 && "dispatchKeyEvent".equals(stackTrace[1].getMethodName())){
//                finanldismiss();
//            }
        }
    }

    public void finanldismiss(){
        super.dismiss();
    }

    public boolean isCanDismiss() {
        return canDismiss;
    }

    public void setCanDismiss(boolean canDismiss) {
        this.canDismiss = canDismiss;
    }


}
