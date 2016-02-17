package com.aimer.shd.gank.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.aimer.shd.gank.Config;
import com.aimer.shd.gank.R;
import com.aimer.shd.gank.adapter.GankAdapter;
import com.aimer.shd.gank.api.ApiImp;
import com.aimer.shd.gank.api.RequestCallbackListener;
import com.aimer.shd.gank.base.BaseRecyclerViewAdapter;
import com.aimer.shd.gank.model.Gank;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by shd on 2016/1/29.
 */
public class ArticleFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, BaseRecyclerViewAdapter.OnItemClickListener<Gank> {

    @Bind(R.id.recyclerView_article_fragment)
    RecyclerView mRecyclerView;
    @Bind(R.id.swipeRefresh_article_fragment)
    SwipeRefreshLayout mSwipeRefresh;
    private String mType;
    private GankAdapter mGankAdapter;
    private List<Gank> mGankList = new ArrayList<>();
    private ApiImp api;
    private int curPage = 1;
    private int lastVisableItem;

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

        api = new ApiImp();
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        setupBaseView();
        loadData();
    }

    private void loadData() {
        mSwipeRefresh.setRefreshing(true);
        api.getGanks(mType, curPage, new RequestCallbackListener() {

            @Override
            public void onSuccess(List<Gank> lists) {
                if (curPage == 1)
                    mGankList.clear();
                mGankList.addAll(lists);
                mGankAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure() {
                Logger.d("Failure");
            }
        });
        mSwipeRefresh.setRefreshing(false);
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void setupBaseView() {
        mGankAdapter = new GankAdapter(getActivity(), mGankList);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(mGankAdapter);
        mRecyclerView.addOnScrollListener(mOnScrollListener);
        mSwipeRefresh.setColorSchemeColors(getResources().getColor(R.color.colorPrimary), getResources().getColor(R.color.colorAccent));
        mSwipeRefresh.setOnRefreshListener(this);

        mGankAdapter.setOnItemClickListener(this);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisableItem + 1 == mGankAdapter.getItemCount()) {
                    curPage++;
                    loadData();
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisableItem = ((LinearLayoutManager) mRecyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }
        });
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
        curPage = 1;
        loadData();
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

    @Override
    public void onItemClick(View view, int position, Gank bean) {
        Toast.makeText(getActivity(), bean.getDesc(), Toast.LENGTH_SHORT).show();
        Intent intent=new Intent(getActivity(),WebViewActivity.class);
        intent.putExtra(getString(R.string.url),bean.getUrl());
        startActivity(intent);
    }


}
