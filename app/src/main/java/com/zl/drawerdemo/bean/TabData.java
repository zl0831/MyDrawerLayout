package com.zl.drawerdemo.bean;

import java.util.List;

public class TabData {
    //每一块tab所属范围的标题
    private String title;

    //每一范围的tab的名字集合
    private List<TabItemData> items;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<TabItemData> getItems() {
        return items;
    }

    public void setItems(List<TabItemData> items) {
        this.items = items;
    }
}
