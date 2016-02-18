package com.aimer.shd.gank.ui;

import android.os.Bundle;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.aimer.shd.gank.R;
import com.aimer.shd.gank.base.ToolbarActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebViewActivity extends ToolbarActivity {


    @Bind(R.id.web)
    WebView mWeb;
    private String url;

    @Override
    protected int provideContentViewId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        url = getIntent().getStringExtra(getString(R.string.url));
        initWebView();
    }

    @Override
    protected boolean canBack() {
        return true;
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
