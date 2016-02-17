package com.aimer.shd.gank.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aimer.shd.gank.R;
import com.aimer.shd.gank.base.BaseRecyclerViewAdapter;
import com.aimer.shd.gank.base.BaseRecyclerViewHolder;
import com.aimer.shd.gank.model.Gank;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shd on 2016/1/27.
 */
public class GankAdapter extends BaseRecyclerViewAdapter<Gank> {


    public GankAdapter(Context context, List<Gank> list) {
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
        viewHolder.mTitleItem.setText(mList.get(position).getDesc());
        viewHolder.mDateItem.setText(mList.get(position).getCreatedAt());
        viewHolder.mUserItem.setText(mList.get(position).getWho());
        if (onItemClickListener != null) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(viewHolder.itemView, position, mList.get(position));
                }
            });

        }

    }

    static class ViewHolder extends BaseRecyclerViewHolder {
        @Bind(R.id.title_item)
        TextView mTitleItem;
        @Bind(R.id.user_item)
        TextView mUserItem;
        @Bind(R.id.date_item)
        TextView mDateItem;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
