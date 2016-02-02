package com.aimer.shd.gank;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.aimer.shd.gank.adapter.CommonItemAdapter;
import com.aimer.shd.gank.model.Gank;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shd on 2016/1/29.
 */
public class ArticleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.recyclerView_article_fragment)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefresh_article_fragment)
    SwipeRefreshLayout mSwipeRefresh;
    private String mType;
    private CommonItemAdapter mCommonItemAdapter;
    private List<Gank> mGankList = new ArrayList<>();

    public static ArticleFragment getInstance(String type) {
        Bundle bundle = new Bundle();
        bundle.putString(Config.type, type);
        ArticleFragment mFragment = new ArticleFragment();
        mFragment.setArguments(bundle);
        return mFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getString(Config.type);
        mCommonItemAdapter = new CommonItemAdapter(getActivity(), mGankList);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupBaseView();
    }

    private void setupBaseView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(mCommonItemAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent));
        mSwipeRefresh.setOnRefreshListener(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {

        return inflater.inflate(R.layout.fragment_article, container, false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {

    }

    private RecyclerView.OnScrollListener mOnScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };
}
