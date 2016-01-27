package com.aimer.shd.gank;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @InjectView(R.id.mSwipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    @InjectView(R.id.mToolBar)
    Toolbar mToolBar;
    @InjectView(R.id.mAppBar)
    AppBarLayout mAppBar;
    @InjectView(R.id.drawer_layout)
    CoordinatorLayout mDrawerLayout;
    @InjectView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private LinearLayoutManager layoutManager;
    private List<String> dataList;
    private boolean refreshing = false;
    private int lastVisibleItem;
    private GankAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        setSupportActionBar(mToolBar);

        mSwipeRefresh.setOnRefreshListener(this);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        dataList = getData();
        mAdapter = new GankAdapter(this, dataList);
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == mAdapter.getItemCount()) {
                    refreshing = true;
                    mSwipeRefresh.setRefreshing(true);
                    loadMore();
                }
            }
        });
    }

    private void loadMore() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                int end = dataList.size() - 1;
                for (int i = end; i < end + 10; i++) {
                    list.add(String.valueOf(i + 1));
                }
                dataList.addAll(list);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        mAdapter.notifyDataSetChanged();
                        refreshing = false;
                        mSwipeRefresh.setRefreshing(false);
                    }
                });
            }
        }).start();


    }

    public List<String> getData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.add(String.valueOf(i + 1));
        }
        return list;
    }

    @Override
    public void onRefresh() {
        dataList.add(0, "new");
        mAdapter.notifyDataSetChanged();
        mSwipeRefresh.setRefreshing(false);
    }
}
