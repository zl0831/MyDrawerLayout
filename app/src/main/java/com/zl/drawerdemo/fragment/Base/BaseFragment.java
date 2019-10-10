package com.zl.drawerdemo.fragment.Base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {
    //获取TAG的fragment名称
    protected final String TAG = this.getClass().getSimpleName();
    //上下文
    public Context context;
    //Fragment的回调
    public EventCallBack mEventCallBack;

    /**
     * 封装Toast对象
     */
    private Toast toast;

    @Override
    public void onAttach(Context ctx) {
        super.onAttach(ctx);
        context = ctx;

        /**
         * 这是默认在Activity中创建Fragment时，给回调赋值，这种方法必须让Activity继承Fragment的回调接口
         * 如不想让Activity继承Fragment的回调接口，则可以注销这行，然后在Activity调用Fragment的SetEventCallBack方法来动态增加回调
         */
        if(ctx instanceof EventCallBack)
            mEventCallBack = (EventCallBack) ctx;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(initLayout(), container, false);
        initView(rootView);
        initData(context);

        return rootView;
    }

    /**
     * 初始化布局
     *
     * @return 布局id
     */
    protected abstract int initLayout();

    /**
     * 初始化控件
     *
     * @param rootView 布局View(充当Fragment的最外层布局）
     */
    protected abstract void initView(final View rootView);

    /**
     * 初始化、绑定数据
     *
     * @param mContext 上下文
     */
    protected abstract void initData(Context mContext);


    /**
     *  用这个方法可以自定义Activity中的回调方法，不必须Activity继承Fragment中的回调接口
     * @param mEventCallBack 自定义的Fragment的回调接口
     */
    public void SetEventCallBack(EventCallBack mEventCallBack){
        this.mEventCallBack = mEventCallBack;
    }


    /**
     * 保证同一按钮在1秒内只响应一次点击事件
     */
    public abstract class OnSingleClickListener implements View.OnClickListener {
        public OnSingleClickListener(){

        }

        public OnSingleClickListener(int delay_time){
            MIN_CLICK_DELAY_TIME = delay_time;
        }

        //两次点击按钮的最小间隔，目前为1000
        private int MIN_CLICK_DELAY_TIME = 1000;
        private long lastClickTime;

        public abstract void onSingleClick(View view);

        @Override
        public void onClick(View v) {
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                lastClickTime = curClickTime;
                onSingleClick(v);
            }
        }
    }


    /**
     * 同一按钮在短时间内可重复响应点击事件
     */
    public abstract class OnMultiClickListener implements View.OnClickListener {
        public abstract void onMultiClick(View view);

        @Override
        public void onClick(View v) {
            onMultiClick(v);
        }
    }


    /**
     * 测试写一个回调接口,这个回调可以实现让Fragment与Activity通信
     * 如有这个需要，则需要让Activity实现这个接口
     */
    public interface EventCallBack{

        /**
         *  无参无返回
         */
        void OnActivity();
    }


    /**
     * 显示提示  toast
     *
     * @param msg 提示信息
     */
    public void showToast(String msg) {
        if (null == toast) {
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else {
            toast.setText(msg);
        }
        toast.show();
    }

    /**
     * 显示提示  toast
     *
     * @param StringId 字符串id
     */
    public void showToast(@StringRes int StringId) {
        if (null == toast) {
            toast = Toast.makeText(context, StringId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(StringId);
        }
        toast.show();
    }

    @Override
    public void onDetach() {
        super.onDetach();

        context = null;
        mEventCallBack = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

}
