package com.aimer.shd.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shd on 2016/1/29.
 */
public class ArticleFragment extends Fragment {

    private static ArticleFragment mFragment;
    @Bind(R.id.recyclerView_article_fragment)
    RecyclerView mRecyclerViewArticleFragment;
    @Bind(R.id.swipeRefresh_article_fragment)
    SwipeRefreshLayout mSwipeRefreshArticleFragment;
    private String mType;

    public static ArticleFragment getInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(Config.type,type);
        if (null == mFragment) {
            mFragment = new ArticleFragment();
        }
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getString(Config.type);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_article, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
