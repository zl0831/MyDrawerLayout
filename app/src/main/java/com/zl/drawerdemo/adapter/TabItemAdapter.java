package com.zl.drawerdemo.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.drawerdemo.R;

import java.util.List;

public class TabItemAdapter extends QuickAdapter<String> implements QuickAdapter.OnItemClickListener {

    public TabItemAdapter(List<String> datas, Context context) {
        super(datas, context);
    }

    public TabItemAdapter(List<String> datas, Context context, int divider_offset) {
        super(datas, context, divider_offset);
    }

    @Override
    public int getLayoutId(int viewType) {
        return R.layout.tab_item_item;
    }

    @Override
    public void convert(final VH holder, String data, int position) {
        TextView text = holder.getView(R.id.item_title_tv);
        text.setText(data);

        setOnItemClickListener(this);
    }


    @Override
    public void onItemClick(VH holder, int position) {
        Toast.makeText(holder.itemView.getContext(), "单击: " + (String) holder.getMdata(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onItemLongClick(VH holder, int position) {
        Toast.makeText(holder.itemView.getContext(), "长按: " + (String) holder.getMdata(), Toast.LENGTH_SHORT).show();
    }
}
