package com.zl.drawerdemo.adapter.Tab;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.drawerdemo.R;
import com.zl.drawerdemo.adapter.Base.QuickAdapter;
import com.zl.drawerdemo.bean.TabItemData;

import java.util.List;

public class TabItemAdapter extends QuickAdapter<TabItemData> implements QuickAdapter.OnItemClickListener {

    public TabItemAdapter(List<TabItemData> datas, Context context) {
        super(datas, context);
    }

    public TabItemAdapter(List<TabItemData> datas, Context context, int divider_offset) {
        super(datas, context, divider_offset);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.tab_item_item;
    }

    @Override
    public void convert(final VH holder, TabItemData data, int position) {
        TextView text = holder.getView(R.id.item_title_tv);
        text.setText(data.getName());

        setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(VH holder, int position) {
        Toast.makeText(holder.itemView.getContext(), "单击: " + ((TabItemData)holder.getMdata()).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(VH holder, int position) {
        Toast.makeText(holder.itemView.getContext(), "长按: " + ((TabItemData)holder.getMdata()).getName(), Toast.LENGTH_SHORT).show();
    }
}
