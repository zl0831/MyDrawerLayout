package com.zl.drawerdemo.adapter.Tab;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.zl.drawerdemo.R;
import com.zl.drawerdemo.adapter.Base.QuickAdapter;
import com.zl.drawerdemo.bean.TabData;

import java.util.List;


public class TabAdapter extends QuickAdapter<TabData> {

    public TabAdapter(List<TabData> datas, Context context) {
        super(datas,context);
    }

    public TabAdapter(List<TabData> datas, Context context, int divider_offset) {
        super(datas, context, divider_offset);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.tab_item;
    }


    @Override
    public void convert(final VH holder, TabData data, int position) {
        RecyclerView item_root_rv = holder.getView(R.id.item_root_rv);

        LinearLayoutManager manager = new LinearLayoutManager(mConText);
        item_root_rv.setLayoutManager(manager);

        TabItemAdapter adapter = new TabItemAdapter(data.getItems(),mConText,2);
        item_root_rv.setAdapter(adapter);
    }
}
