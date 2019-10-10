package com.zl.drawerdemo.adapter.Base;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


/**
 * 通用的Adapter，再新加adapter只需要继承这个类，重写getLayoutId、convert方法，即可快速创建完成
 * @param <T> 数据类型
 */
public abstract class QuickAdapter<T> extends RecyclerView.Adapter<QuickAdapter.VH> {
    public final String TAG = this.getClass().getSimpleName();
    //数据
    private List<T> mDatas;
    // 事件回调监听
    private OnItemClickListener onItemClickListener;

    // Item间的间隔大小
    private int divider_offset = 0;

    public Context mConText;

    /**
     * @param datas 数据列表
     * @param context
     */
    public QuickAdapter(List<T> datas,Context context) {
        this.mDatas = datas;
        this.mConText = context;
    }

    /**
     * @param datas 数据列表
     * @param context
     * @param divider_offset Item间的间隔距离(单位是px)
     */
    public QuickAdapter(List<T> datas,Context context,int divider_offset) {
        this.mDatas = datas;
        this.mConText = context;
        this.divider_offset = divider_offset;
    }


    /**
     * RecyclerView下Item的布局Id
     * @param viewType
     * @return
     */
    public abstract int getLayoutId(int viewType);

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        return VH.get(parent, getLayoutId(viewType));
    }

    @Override
    public void onBindViewHolder(VH holder, final int position) {
        T data = mDatas.get(position);
        //绑定数据
        holder.setMdata(data);
        //处理Item,这个抽象方法在各个子Adapter中根据不同需求具体实现
        convert(holder,data,position);
        //给除了最后一个的Item的其他Item添加一个下边距，产生间隔的效果
        Initdivider(holder, position);
        //绑定每个Item的点击和长按事件
        InitOnlcikEvent(holder);
    }

    @Override
    public int getItemCount() {
        return mDatas == null ? 0 : mDatas.size();
    }

    public abstract void convert(final VH holder,T data,int position);

    public void Initdivider(VH holder,int position){
        //设置边距，产生每个Item之间有点间隔的效果
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams();
        if (position != getItemCount() - 1) {
            //params.setMargins(0, 0, 0, 30);
            params.bottomMargin = divider_offset;
        }
    }

    public void InitOnlcikEvent(final VH holder){
        //绑定点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemClick(holder, pos);
                }
            }
        });


        //绑定长按事件
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onItemClickListener != null) {
                    int pos = holder.getLayoutPosition();
                    onItemClickListener.onItemLongClick(holder, pos);
                }

                //返回true表明此事件已被响应，长按后不会再调用单击事件，否则若返回false，则不仅会响应长按事件，还会响应单击事件
                return true;
            }
        });
    }

    // 定义点击回调接口
    public interface OnItemClickListener {
        void onItemClick(VH view, int position);

        void onItemLongClick(VH view, int position);
    }

    // 定义一个设置点击监听器的方法
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }


    // VH类
    public static class VH extends RecyclerView.ViewHolder {
        //存储各个子控件
        private SparseArray<View> mViews;
        //Item的根控件(根布局)
        private View mConvertView;
        //Item的数据
        private Object mdata;



        private VH(View v) {
            super(v);
            mConvertView = v;
            mViews = new SparseArray<>();
        }


        public static VH get(ViewGroup parent,@LayoutRes int layoutId) {
            View convertView = LayoutInflater.from(parent.getContext()).inflate(layoutId, parent, false);
            return new VH(convertView);
        }

        public <T extends View> T getView(@IdRes int id) {
            View v = mViews.get(id);
            if (v == null) {
                v = mConvertView.findViewById(id);
                mViews.put(id, v);
            }
            return (T) v;
        }

        public Object getMdata() {
            return mdata;
        }

        public void setMdata(Object mdata) {
            this.mdata = mdata;
        }


    }


}
