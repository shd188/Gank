package com.aimer.shd.gank.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.aimer.shd.gank.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.Bind;
import butterknife.BindColor;
import butterknife.ButterKnife;

public class WebViewActivity extends AppCompatActivity {

    @Bind(R.id.web)
    WebView mWeb;
    @Bind(R.id.toolbar_web)
    Toolbar mToolbarWeb;
    @BindColor(R.color.colorPrimaryDark)
    int colorPrimaryDark;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra(getString(R.string.url));
        initView();
        initWebView();
    }

    private void initView() {
        setSupportActionBar(mToolbarWeb);
        SystemBarTintManager manager=new SystemBarTintManager(this);
        manager.setStatusBarTintEnabled(true);
        manager.setNavigationBarTintEnabled(true);
        manager.setStatusBarTintColor(colorPrimaryDark);
        mToolbarWeb.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void initWebView() {
        WebSettings webSetting = mWeb.getSettings();
        webSetting.setJavaScriptEnabled(true);
        webSetting.setAppCacheEnabled(true);
        webSetting.setSupportZoom(true);
        webSetting.setLoadWithOverviewMode(true);
        webSetting.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        mWeb.setWebChromeClient(new ChromeClient());
        mWeb.setWebViewClient(new MyWebViewClient());
        mWeb.loadUrl(url);
    }

    private class ChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            super.onProgressChanged(view, newProgress);
//            mProgressbar.setProgress(newProgress);
//            if (newProgress == 100) {
//                mProgressbar.setVisibility(View.GONE);
//            } else {
//                mProgressbar.setVisibility(View.VISIBLE);
//            }

        }

        @Override
        public void onReceivedTitle(WebView view, String title) {
            super.onReceivedTitle(view, title);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url != null) view.loadUrl(url);
            return true;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (mWeb.canGoBack())
                        mWeb.goBack();
                    else
                        finish();
                    break;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
