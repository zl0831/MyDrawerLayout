package com.zl.drawerdemo.xml;

import android.util.Log;

import com.zl.drawerdemo.bean.TabData;
import com.zl.drawerdemo.bean.TabItemData;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class TabDataSax extends DefaultHandler {
    private final String TAG = this.getClass().getSimpleName();
    private TabData tabData;
    private TabItemData tabItemData;
    private List<TabData> tabDatas;
    private List<TabItemData> items;

    private String tagName = null;

    @Override
    public void startDocument() throws SAXException {
        this.tabDatas = new ArrayList<>();
        this.items = new ArrayList<>();
        Log.i(TAG, "startDocument: 开始解析XML");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("TabData")) {
            tabData = new TabData();
            Log.i(TAG, "startElement: 开始处理TabData元素");
        } else if (localName.equals("items")) {
            this.items.clear();
            Log.i(TAG, "startElement: 开始处理items元素");
        }else if(localName.equals("item")){
            tabItemData = new TabItemData();
            Log.i(TAG, "startElement: 开始处理item元素");
        }
        this.tagName = localName;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (this.tagName != null) {
            String data = new String(ch, start, length);
            if (this.tagName.equals("title")) {
                tabData.setTitle(data);
            } else if (this.tagName.equals("item")) {
                tabItemData.setName(data);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (localName.equals("TabData")) {
            this.tabDatas.add(tabData);
            tabData = null;
            Log.i(TAG, "endElement: 处理TabData元素结束");
        } else if (localName.equals("items")) {
            tabData.setItems(new ArrayList<>(items));
            Log.i(TAG, "endElement: 处理items元素结束");
        }else if (localName.equals("item")) {
            this.items.add(tabItemData);
            tabItemData = null;
            Log.i(TAG, "endElement: 处理item元素结束");
        }

        this.tagName = null;
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();

        Log.i(TAG, "endDocument: 读取到文档尾，xml解析结束");
    }


    public List<TabData> GetTabDataList() {
        return tabDatas;
    }
}
