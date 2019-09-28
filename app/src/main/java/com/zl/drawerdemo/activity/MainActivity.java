package com.zl.drawerdemo.activity;

import android.os.Bundle;

import com.zl.drawerdemo.R;
import com.zl.drawerdemo.activity.Base.DrawerActivity;


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
