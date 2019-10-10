package com.zl.drawerdemo.activity.Test;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentTransaction;

import com.zl.drawerdemo.R;
import com.zl.drawerdemo.activity.Base.BaseActivity;
import com.zl.drawerdemo.fragment.Base.BaseFragment;
import com.zl.drawerdemo.fragment.Test.Test2Fragment;
import com.zl.drawerdemo.fragment.Test.TestFragment;

public class FragmentActivity extends BaseActivity implements BaseFragment.EventCallBack {

    private FragmentTransaction ft;

    private TestFragment one;
    private Test2Fragment twe;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int InitLayout() {
        return R.layout.activity_fragment;
    }

    @Override
    protected void InitView() {

        OpenFragmentOne();
        OpenFragmentTwe();
    }

    @Override
    protected void InitData() {

    }


    public void OpenFragmentOne() {
        if(one != null)
            return;
        one = new TestFragment();

        one.SetBackColor(R.color.fragment_background1_color);
//        //添加回调函数
//        one.SetEventCallBack(new BaseFragment.EventCallBack() {
//            @Override
//            public void OnActivity() {
//                OnCallBackEvent();
//            }
//        });

        ft = AddFragment(R.id.fragment_content_01, one, "one");
        ft.commit();
    }

    public void OpenFragmentTwe(){
        if(twe != null)
            return;

        //TestFragment twe = new TestFragment();
        twe = new Test2Fragment();

        //twe.SetBackColor(R.color.fragment_background2_color);

        ft = AddFragment(R.id.fragment_content_02, twe, "twe");
        ft.commit();

    }


    //测试Fragment中的回调函数
    public void OnCallBackEvent(){
        Log.i(TAG,"OnActivity: 测试回调函数");
        showToast("测试回调函数");
    }


    @Override
    public void OnActivity() {
        OnCallBackEvent();
    }
}
