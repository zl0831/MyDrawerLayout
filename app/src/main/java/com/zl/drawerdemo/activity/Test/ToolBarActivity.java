package com.zl.drawerdemo.activity.Test;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.zl.drawerdemo.R;

/**
 * 测试下ToolBar
 */
public class ToolBarActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //使用Toolbar之前一定要去掉系统自带的ActionBar,可以给Activity使用NoActionBar主题,
        //或者在setContentView前调用下面两个方法(二各方法选一即可)
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_tool_bar);

        InitToolBar();
    }



    private void InitToolBar(){
        toolbar = findViewById(R.id.toolbar_test);
        TextView title = findViewById(R.id.toolbar_test_title);

        title.setText("标题");

        //当调用toolbar.inflateMenu设置menu时，下面两个方法必须删去，否则系统会调用Activity本身创建menu的接口导致toolbar.inflateMenu失效
        //setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //设置menu
        toolbar.inflateMenu(R.menu.base_toolbar_menu);

        //设置导航栏图标
        toolbar.setNavigationIcon(R.drawable.menu);
        //设置logo图标
        //toolbar.setLogo(R.mipmap.ic_launcher);

        //设置主标题Title
        toolbar.setTitle("");//设置主标题
        toolbar.setTitleTextColor(Color.WHITE);
        //设置主标题文本的字体，大小，颜色等主题属性
        //toolbar.setTitleTextAppearance(getApplicationContext(), R.style.Theme_Toolbar_Base_Title);

        //设置主标题Subtitle
        toolbar.setSubtitle("");//设置子标题
        toolbar.setSubtitleTextColor(Color.TRANSPARENT);

        //设置左侧导航图标(NavigationIcon)点击事件
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText( ToolBarActivity.this, "点击菜单" , Toast.LENGTH_SHORT).show();
            }
        });

        //设置右侧各菜单(menu)点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int resId = item.getItemId();

                switch (resId) {
                    case R.id.action_item1:
                        Toast.makeText( ToolBarActivity.this, R.string.item_01 , Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_item2:
                        Toast.makeText( ToolBarActivity.this, R.string.item_02 , Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText( ToolBarActivity.this, "未知" , Toast.LENGTH_SHORT).show();
                        break;
                }

                return true;
            }
        });
    }

}
