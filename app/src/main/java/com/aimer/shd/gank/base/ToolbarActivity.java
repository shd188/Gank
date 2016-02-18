package com.aimer.shd.gank.base;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.aimer.shd.gank.R;

/**
 * BaseToolbar
 * Created by shd on 2016/2/18.
 */
public abstract class ToolbarActivity extends AppCompatActivity {

    abstract protected int provideContentViewId();

    protected Toolbar mToolbar;
    protected TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(provideContentViewId());

        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        if (mToolbar == null) {
            throw new IllegalStateException("no toolbar");
        }
        setSupportActionBar(mToolbar);
        if (hasTabLayout()) {
            mTabLayout.setVisibility(View.VISIBLE);
        }

        if (canBack()) {
            android.support.v7.app.ActionBar actionBar = getSupportActionBar();
            if (actionBar != null)
                actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    protected boolean hasTabLayout() {
        return false;
    }

    protected boolean canBack() {
        return false;
    }
}
