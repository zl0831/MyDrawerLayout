package com.zl.drawerdemo.bean;

import java.util.List;

public class TabData {
    //每一块tab所属范围的标题
    private String title;

    //每一范围的tab的名字集合
    private List<String> items;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getItems() {
        return items;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }
}
