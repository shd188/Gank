package com.aimer.shd.gank.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by shd on 2016/1/27.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {

    protected Context mContext;
    protected List<T> mList = new ArrayList<>();
    protected LayoutInflater mInflater;
    public OnItemClickListener<T> onItemClickListener;
    private OnItemLongClickListener<T> onItemLongClickListener;

    public BaseRecyclerViewAdapter(Context context, List<T> list) {
        this.mContext = context;
        mInflater = LayoutInflater.from(mContext);
        if (list != null) {
            mList = list;
        }
    }

    public List<T> getDatas() {
        return mList;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void addDataFirst(List<T> datas) {
        mList.addAll(0, datas);
        notifyDataSetChanged();
    }

    public void addDataLast(List<T> datas) {
        mList.addAll(datas);
        notifyDataSetChanged();
    }

    public void remove(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    public void removeAll() {
        mList.clear();
        notifyDataSetChanged();
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return createRecyclerViewHolder(parent, viewType);
    }

    protected abstract BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType);


    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        setData(holder, position);
    }

    protected abstract void setData(BaseRecyclerViewHolder holder, int position);


    @Override
    public int getItemCount() {
        return mList.size();
    }


    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T bean);
    }
    public interface OnItemLongClickListener<T> {
        void onItemClick(View view, int position, T bean);
    }
    public void setOnItemClickListener(OnItemClickListener<T> listener) {
        this.onItemClickListener = listener;
    }
    public void setOnItemLongClickListener(OnItemLongClickListener<T> listener) {
        this.onItemLongClickListener = listener;
    }
}
