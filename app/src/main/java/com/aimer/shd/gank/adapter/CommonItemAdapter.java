package com.aimer.shd.gank.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.aimer.shd.gank.base.BaseRecyclerViewAdapter;
import com.aimer.shd.gank.base.BaseRecyclerViewHolder;
import com.aimer.shd.gank.model.Gank;

import java.util.List;

/**
 * Created by shd on 2016/2/1.
 */
public class CommonItemAdapter extends BaseRecyclerViewAdapter<Gank> {

    public CommonItemAdapter(Context context, List<Gank> list) {
        super(context, list);
    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    protected void setData(BaseRecyclerViewHolder holder, int position) {

    }
}
