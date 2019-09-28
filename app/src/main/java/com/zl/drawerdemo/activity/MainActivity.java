package com.zl.drawerdemo.activity;

import android.os.Bundle;

import com.zl.drawerdemo.R;


/**
 * 直接继承Drawer类
 */
public class MainActivity extends DrawerActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //调用基类中的初始函数
        super.SetContentView(R.layout.activity_drawer);
    }
}
