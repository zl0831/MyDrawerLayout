package com.zl.drawerdemo.fragment.Test;

import android.content.Context;
import android.util.Log;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zl.drawerdemo.R;
import com.zl.drawerdemo.adapter.TabAdapter;
import com.zl.drawerdemo.bean.TabData;
import com.zl.drawerdemo.fragment.Base.BaseFragment;
import com.zl.drawerdemo.xml.TabDataSax;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class Test2Fragment extends BaseFragment {


    private RecyclerView root_rv;

    @Override
    protected int initLayout() {
        return R.layout.fragment_test2;
    }

    @Override
    protected void initView(final View rootView) {
        root_rv = rootView.findViewById(R.id.root_rv);

        //固定高度
        root_rv.setHasFixedSize(true);

    }

    @Override
    protected void initData(Context mContext) {

        LinearLayoutManager manager = new LinearLayoutManager(mContext);
        root_rv.setLayoutManager(manager);

        /**
         * 这儿数据我用的模拟的，随机组成的，用来测试的
         * 正规数据一般是请求服务器，通过服务器动态控制Tab的显隐以及其他（频繁变动或者需求服务器控制的用这种方式）
         * 另外一种就是读取本地，Android上可以把数据写到xml文件中，再解析xml文件读取，然后根据xml文件中的数据显隐Tab
         * 最差就是把数据写死在代码里面
         */
        List<TabData> data = null;

        try {
            data = ReadXML(mContext);
        } catch (Exception e) {
            Log.i(TAG, "initData: 解析xml文件出错");
            e.printStackTrace();
        }

        if(data == null){
            Log.i(TAG, "initData: 数据为null");
            return;
        }

        TabAdapter adapter = new TabAdapter(data,mContext,50);

        root_rv.setAdapter(adapter);

    }

    // SAX方式解析xml文件
    public List<TabData> ReadXML(Context context) throws Exception{
        //获取文件资源建立输入流对象
        InputStream is = context.getAssets().open("TabData.xml");
        //创建XML解析处理器
        TabDataSax sax = new TabDataSax();
        //得到SAX解析工厂
        SAXParserFactory factory = SAXParserFactory.newInstance();
        //创建SAX解析器
        SAXParser parser = factory.newSAXParser();
        //将xml解析处理器分配给解析器,对文档进行解析,将事件发送给处理器
        parser.parse(is,sax);
        is.close();
        return sax.GetTabDataList();
    }



}
