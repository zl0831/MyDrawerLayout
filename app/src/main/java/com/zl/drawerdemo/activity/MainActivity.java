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

    }

    @Override
    protected int InitLayout() {
        return R.layout.activity_drawer;
    }
}
