package com.zl.drawerdemo.fragment.Test;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.ColorRes;

import com.zl.drawerdemo.R;
import com.zl.drawerdemo.fragment.Base.BaseFragment;


public class TestFragment extends BaseFragment{

    private TextView test_fragment_text;
    private Button test_fragment_btn_01;
    private Button test_fragment_btn_02;

    private Integer num = 0;
    private int mBackColor = R.color.navigation_color;

    @Override
    protected int initLayout() {
        return R.layout.fragment_test;
    }


    @Override
    protected void initView(final View rootView) {
        //文本显示
        test_fragment_text = rootView.findViewById(R.id.test_fragment_text);
        //测试按钮
        test_fragment_btn_01 = rootView.findViewById(R.id.test_fragment_btn_01);
        test_fragment_btn_02 = rootView.findViewById(R.id.test_fragment_btn_02);

        rootView.setBackgroundResource(mBackColor);
    }


    @Override
    protected void initData(Context mContext) {
        test_fragment_btn_01.setOnClickListener(single1);
        test_fragment_btn_02.setOnClickListener(single2);
    }


    /**
     * 设置Fragment的背景颜色值
     * @param resid 颜色值ID
     */
    public void SetBackColor(@ColorRes int resid){
        mBackColor = resid;

        //Log.i(TAG, "SetBackColor: " + resid);
    }


    OnSingleClickListener single1 = new OnSingleClickListener() {
        @Override
        public void onSingleClick(View view) {
            num++;
            test_fragment_text.setText(num.toString());
            showToast(R.string.test_fragment_01);

            OnclickCallback();
        }
    };

    OnSingleClickListener single2 = new OnSingleClickListener(5000) {
        @Override
        public void onSingleClick(View view) {
            num++;
            test_fragment_text.setText(num.toString());
            showToast(R.string.test_fragment_01);
        }
    };

}
