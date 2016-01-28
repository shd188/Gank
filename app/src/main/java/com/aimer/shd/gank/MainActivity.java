package com.aimer.shd.gank;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.aimer.shd.gank.adapter.GankAdapter;
import com.aimer.shd.gank.base.BaseRecyclerViewAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    @Bind(R.id.mSwipeRefresh)
    SwipeRefreshLayout mSwipeRefresh;
    @Bind(R.id.mToolBar)
    Toolbar mToolBar;
    @Bind(R.id.mAppBar)
    AppBarLayout mAppBar;
    @Bind(R.id.drawer_layout)
    CoordinatorLayout mDrawerLayout;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindColor(R.color.colorPrimaryDark)
    int colorPrimaryDark;

    private LinearLayoutManager layoutManager;
    private List<String> dataList;
    private boolean refreshing = false;
    private int lastVisibleItem;
    private GankAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(mToolBar);

        // 创建状态栏的管理实例
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // 激活状态栏设置
        tintManager.setStatusBarTintEnabled(true);
        // 激活导航栏设置
        tintManager.setNavigationBarTintEnabled(true);

        tintManager.setStatusBarTintColor(colorPrimaryDark);


        mSwipeRefresh.setOnRefreshListener(this);
        layoutManager = new LinearLayoutManager(MainActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        dataList = getData();
        mAdapter = new GankAdapter(this, dataList);
        mRecyclerView.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new BaseRecyclerViewAdapter.OnItemClickListener<String>() {
            @Override
            public void onItemClick(View view, int position, String bean) {
                Toast.makeText(MainActivity.this, "bean--" + bean, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, int position, String bean) {

            }


        });
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

    public List<String> getRefreshData() {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(String.valueOf(i + 1));
        }
        return list;
    }

    @Override
    public void onRefresh() {
        mAdapter.addDataFirst(getRefreshData());
        mSwipeRefresh.setRefreshing(false);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Toast.makeText(MainActivity.this, "setting", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
}
