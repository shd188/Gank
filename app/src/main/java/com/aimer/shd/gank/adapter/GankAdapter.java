package com.aimer.shd.gank.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aimer.shd.gank.R;
import com.aimer.shd.gank.base.BaseRecyclerViewAdapter;
import com.aimer.shd.gank.base.BaseRecyclerViewHolder;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shd on 2016/1/27.
 */
public class GankAdapter extends BaseRecyclerViewAdapter<String> {

    public GankAdapter(Context context, List<String> list) {
        super(context, list);
    }

    @Override
    protected BaseRecyclerViewHolder createRecyclerViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.item_gank, parent, false);
        return new ViewHolder(view);
    }

    @Override
    protected void setData(BaseRecyclerViewHolder holder, final int position) {
        final ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.mTvItem.setText(mList.get(position));
        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(viewHolder.itemView, position, mList.get(position));

                }
            });

            viewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    onItemClickListener.onItemLongClick(viewHolder.itemView, position, mList.get(position));
                    return true;
                }
            });


        }

    }

    static class ViewHolder extends BaseRecyclerViewHolder {
        @Bind(R.id.tv_item)
        TextView mTvItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
