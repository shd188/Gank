package com.aimer.shd.gank.ui;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.aimer.shd.gank.R;
import com.aimer.shd.gank.adapter.MainPagerAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.mToolBar)
    Toolbar mToolBar;
    @Bind(R.id.mAppBar)
    AppBarLayout mAppBar;
    @Bind(R.id.drawer_layout)
    CoordinatorLayout mDrawerLayout;
    @BindColor(R.color.colorPrimaryDark)
    int colorPrimaryDark;
    @Bind(R.id.mTabLayout)
    TabLayout mTabLayout;
    @Bind(R.id.mViewPager)
    ViewPager mViewPager;

    private MainPagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        configView();
        initViewPager();
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private void initViewPager() {mPagerAdapter = new MainPagerAdapter(getSupportFragmentManager());
        mPagerAdapter.addFragment(ArticleFragment.getInstance(getString(R.string.Android)), getString(R.string.Android));
        mPagerAdapter.addFragment(ArticleFragment.getInstance(getString(R.string.iOS)), getString(R.string.iOS));
        mPagerAdapter.addFragment(ArticleFragment.getInstance(getString(R.string.resource)), getString(R.string.resource));
        mPagerAdapter.addFragment(ArticleFragment.getInstance(getString(R.string.recommend)), getString(R.string.recommend));
        mViewPager.setAdapter(mPagerAdapter);
    }

    private void configView() {
        setSupportActionBar(mToolBar);

        // 创建状态栏的管理实例
        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        // 激活状态栏设置
        tintManager.setStatusBarTintEnabled(true);
        // 激活导航栏设置
        tintManager.setNavigationBarTintEnabled(true);

        tintManager.setStatusBarTintColor(colorPrimaryDark);

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
