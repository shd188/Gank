package com.aimer.shd.gank.ui;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aimer.shd.gank.R;
import com.aimer.shd.gank.adapter.MainPagerAdapter;
import com.aimer.shd.gank.base.ToolbarActivity;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;

public class MainActivity extends ToolbarActivity {

    @BindColor(R.color.colorPrimaryDark)
    int colorPrimaryDark;
    @Bind(R.id.mViewPager)
    ViewPager mViewPager;
    @Bind(R.id.tabLayout)
    TabLayout mTabLayout;

    private MainPagerAdapter mPagerAdapter;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

//        configView();
        initViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initViewPager() {
        mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(ArticleFragment.getInstance(getString(R.string.Android)), getString(R.string.Android));
        mPagerAdapter.addFragment(ArticleFragment.getInstance(getString(R.string.iOS)), getString(R.string.iOS));
        mPagerAdapter.addFragment(ArticleFragment.getInstance(getString(R.string.resource)), getString(R.string.resource));
        mPagerAdapter.addFragment(ArticleFragment.getInstance(getString(R.string.recommend)), getString(R.string.recommend));
        mViewPager.setAdapter(mPagerAdapter);
    }

//    private void configView() {
//        // 创建状态栏的管理实例
//        SystemBarTintManager tintManager = new SystemBarTintManager(this);
//        // 激活状态栏设置
//        tintManager.setStatusBarTintEnabled(true);
//        // 激活导航栏设置
//        tintManager.setNavigationBarTintEnabled(true);
//
//        tintManager.setStatusBarTintColor(colorPrimaryDark);
//
//    }


    @Override
    protected boolean hasTabLayout() {
        return true;
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
