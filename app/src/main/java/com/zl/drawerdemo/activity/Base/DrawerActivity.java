package com.zl.drawerdemo.activity.Base;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.zl.drawerdemo.R;
import com.zl.drawerdemo.pupwindow.CommonPop;

/**
 * 尝试封装成基类Drawer（测试版，不全，未整理）
 */
public abstract class DrawerActivity extends BaseActivity {
    // 抽屉菜单对象
    public DrawerLayout drawerLayout;
    private RelativeLayout main_left_drawer_layout, main_right_drawer_layout;
    private Toolbar toolbar;
    private NavigationView navigation_view;
    private WindowManager.LayoutParams params;
    private CommonPop TestPopWin;
    private RelativeLayout content_parent;

    //控制抽屉滑动时，主内容的缩放和Alpha，可以封装成接口给控制，就可实现滑动时，主内容区域是否缩放或者Aplha是否变化
    private float DrawerScale = 1.0f;
    private float DrawerAlpha = 0.5f;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        InitView();
        InitData();
    }


    /**
     * 使用ToolBar则需要把app默认的actionbar去掉或者隐藏，toolbar要好用一些，可以在AndroidManifest.xml修改android:theme属性
     */
    public void InitToolBar() {
        toolbar = findViewById(R.id.drawer_toolbar);
        TextView toolbarTitle = findViewById(R.id.drawer_toolbar_title);


        toolbarTitle.setText("新浪微博");


        //设置右上角menu
        toolbar.inflateMenu(R.menu.base_toolbar_menu);

        //设置左上角NavigationIcon
        toolbar.setNavigationIcon(R.drawable.menu);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenLeftLayout();
            }
        });

        //设置右上角各个menuItem的点击事件
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                int resId = item.getItemId();
                switch (resId) {
                    case R.id.action_item1:
                        Toast.makeText(DrawerActivity.this, R.string.item_01, Toast.LENGTH_SHORT).show();
                        openRightLayout();
                        break;
                    case R.id.action_item2:
                        Toast.makeText(DrawerActivity.this, R.string.item_02, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }

                return true;
            }
        });

    }

    @Override
    protected abstract int InitLayout();

    @Override
    protected void InitView() {
        /**
         * 整个DrawerLayout布局，google原生态的抽屉布局
         **/
        drawerLayout = findViewById(R.id.main_drawer_layout);

        //设置菜单内容之外其他区域的背景色
        drawerLayout.setScrimColor(Color.TRANSPARENT);


        /**
         * 中间主题布局，手机上中间显示的区域需要定义在这儿
         **/
        content_parent = findViewById(R.id.content_parent);

        /**
         * 左边菜单
         * 使用默认布局，侧滑出来的布局完全需要自定义
         * */
        //main_left_drawer_layout = findViewById(R.id.left_drawer_layout);

        /**
         * 使用google封装好的navigation_view布局，需要引入com.google.android.material:material:1.0.0包
         *
         * */
        navigation_view = findViewById(R.id.navigation_view);

        /**
         * 右边菜单
         * 使用默认布局，侧滑出来的布局完全需要自定义
         * */
        main_right_drawer_layout = findViewById(R.id.right_drawer_layout);

        InitToolBar();
        InitLayout();
        InitEvent();
    }

    @Override
    protected void InitData() {


        //获取系统参数
        params = getWindow().getAttributes();

    }


    /**
     * 设置navigation_view中的各种监听事件
     */
    private void InitNavEvent() {
        //获取navigation_view头部布局
        View headView = navigation_view.getHeaderView(0);

        /**
         *  设置navigation_view头部的点击事件
         **/
        headView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /**
         * 设置navigation_view中下面各个menu选项的点击事件
         **/
        navigation_view.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                switch (itemId) {
                    case R.id.navigation_item_01:
                        Toast.makeText(DrawerActivity.this, R.string.navigation_item_01, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_item_02:
                        Toast.makeText(DrawerActivity.this, R.string.navigation_item_02, Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.navigation_item_03:
                        Toast.makeText(DrawerActivity.this, R.string.navigation_item_03, Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });

    }

    private void InitDrawerEvent(){
        /**
         * 给drawerLayout设置各种监听
         **/
        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {

            /**
             * 抽屉滑动时的监听
             * @param drawerView
             * @param slideOffset
             */
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                /**
                 *  这儿控制抽屉滑动时的各种操作，目前实现了主内容布局随着抽屉滑动而改变位置和缩放以及Alpha
                 **/

                float scale = 1 - DrawerScale;
                float distance = drawerView.getMeasuredWidth() * slideOffset;

                //设置主内容滑动
                if (drawerView.getId() == R.id.navigation_view) {
                    content_parent.setTranslationX(distance * DrawerScale);

                } else {
                    content_parent.setTranslationX(-distance * DrawerScale);

                }

                //设置主内容Alpha
                content_parent.setAlpha(1 - slideOffset * (1 - DrawerAlpha));
                //设置主内容缩放
                content_parent.setScaleX(1 - slideOffset * scale);
                content_parent.setScaleY(1 - slideOffset * scale);

            }

            /**
             * 抽屉完全展开时的监听
             * @param drawerView
             */
            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            /**
             * 抽屉完全关闭时的监听
             * @param drawerView
             */
            @Override
            public void onDrawerClosed(@NonNull View drawerView) {

            }

            /**
             * 抽屉状态改变时的监听
             * @param newState
             */
            @Override
            public void onDrawerStateChanged(int newState) {


            }
        });
    }


    //设置各种监听事件
    private void InitEvent() {
        InitNavEvent();
        InitDrawerEvent();
    }



    //左边菜单开关事件
    public void openLeftLayout(View view) {
        OpenLeftLayout();
    }

    private void OpenLeftLayout() {
        View view = navigation_view;//main_left_drawer_layout
        if (drawerLayout.isDrawerOpen(view)) {
            drawerLayout.closeDrawer(view);
        } else {
            drawerLayout.openDrawer(view);
        }
    }

    // 右边菜单开关事件
    public void openRightLayout(View view) {
        openRightLayout();
    }

    private void openRightLayout() {
        View view = main_right_drawer_layout;

        if (drawerLayout.isDrawerOpen(view)) {
            drawerLayout.closeDrawer(view);
        } else {
            drawerLayout.openDrawer(view);
        }
    }


    /**
     * 这个是开始打算用PopupWindow来创建下面的弹窗，类似dialog，自定义位置，比dialog灵活
     * 但是发现不适合嵌入到当前界面，因为这个是弹窗，是显示在最上层的，会遮挡其他控件及布局，
     * 比如会遮挡左滑动栏，嵌入的话更好是用动态加载布局的方式
     * @param view
     */
    public void openBottomLayout(View view) {
        TestPopWin = new CommonPop(this, R.layout.pop_layout,content_parent,false);
        TestPopWin.setOutsideTouchable(false);
        TestPopWin.setTouchable(false);
        //TestPopWin.setCanDismiss(false);
        //设置Popupwindow显示位置（从底部弹出）
        TestPopWin.showAtLocation(content_parent, Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);
        //SetPopWinOnDismiss();
        TestPopWin.SetCloseBtn(R.id.pop_layout_Close);
    }

    //弹窗关闭时的设置
    public void SetPopWinOnDismiss(){
        //当弹出Popupwindow时，背景变半透明
        params.alpha = 0.7f;
        getWindow().setAttributes(params);
        //设置Popupwindow关闭监听，当Popupwindow关闭，背景恢复1f
        TestPopWin.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
    }


    /**
     * 用LayoutInflater动态加载布局，这个类似于动态加载一个想要的布局嵌入到另外一个布局，
     * 在UI显示上的层级是和被嵌入的布局的层级一样，不会遮挡其他布局
     * 可以自己再单独封装成同一个公用的动态加载布局的类或者方法，不用封装到Drawer的基类中
     **/
    public void OpenTest(View view) {
        /**
         * 这儿动态创建的view可以设为全局变量，这样可以在开始的时候判断是否为空，为空则创建，不为空就直接引用,
         * 然后隐藏的时候就不用了removeView去除了，可以用setVisibility控制显隐,节省频繁动态创建销毁的消耗
         **/
        final View pop_layout = LayoutInflater.from(this).inflate(R.layout.pop_layout, content_parent,false);
        RelativeLayout.LayoutParams params =  (RelativeLayout.LayoutParams) pop_layout.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        pop_layout.setLayoutParams(params);

        Button pop_layout_Close = pop_layout.findViewById(R.id.pop_layout_Close);

        pop_layout_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                content_parent.removeView(pop_layout);
                //pop_layout.setVisibility(View.VISIBLE);
            }
        });

        //这儿设置这个是为了防止点击穿透
        pop_layout.setClickable(true);

        content_parent.addView(pop_layout);
    }


}
