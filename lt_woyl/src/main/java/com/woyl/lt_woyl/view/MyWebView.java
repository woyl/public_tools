
package com.woyl.lt_woyl.view;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.woyl.lt_woyl.R;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * 自定义webview
 *
 * @author lj
 * @date 2018-8-13 下午03:23:05
 */
public class MyWebView extends WebView {
    private ProgressBar mProgressBar;
    //监听器
    private MyWebViewListener listener;

    public MyWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
        mProgressBar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 8);
        mProgressBar.setLayoutParams(layoutParams);
        Drawable drawable = context.getResources().getDrawable(R.drawable.progress_bar_states);
        mProgressBar.setProgressDrawable(drawable);
        addView(mProgressBar);
        setWebChromeClient(new WebChromeClient());
    }

    @SuppressLint("SetJavaScriptEnabled")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void init() {
        this.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
        WebSettings wset = this.getSettings();
        //允许访问js
        wset.setJavaScriptEnabled(true);
        //避免https的网页的图片用http被block
        wset.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        //设置编码
        wset.setDefaultTextEncodingName("utf-8");
        //设置不缓存
        wset.setCacheMode(WebSettings.LOAD_NO_CACHE);
        //允许js缓存
        wset.setUserAgentString("dr-android");
        wset.setAllowFileAccess(true);
        wset.setDomStorageEnabled(true);
        wset.setDatabaseEnabled(true);
        wset.setAppCacheEnabled(true);
        wset.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        wset.setUseWideViewPort(true);
        wset.setLoadWithOverviewMode(true);
        wset.setSupportZoom(true);
        wset.setTextZoom(100);

        //window.dr.callback(type, data);
        this.addJavascriptInterface(new Object() {
            @android.webkit.JavascriptInterface
            public void drshopwechatpay(String str) throws UnsupportedEncodingException {
                str = URLDecoder.decode(str, "UTF-8");
                listener.onJsCallBack(str, "payWechat");
            }

            @android.webkit.JavascriptInterface
            public void drthemeredirect(String str) throws UnsupportedEncodingException {
                str = URLDecoder.decode(str, "UTF-8");
                listener.onJsCallBack(str, "newPage");
            }

            @android.webkit.JavascriptInterface
            public void backToHome() throws UnsupportedEncodingException {
                listener.onJsCallBack("", "close");
            }
        }, "android");


        this.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return true;
            }

            //加载失败监听
            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
//                loadUrl("file:///android_asset/showerror.html");
            }
        });
    }

    public class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                mProgressBar.setVisibility(GONE);
            } else {
                if (mProgressBar.getVisibility() == GONE) {
                    mProgressBar.setVisibility(VISIBLE);
                }
                mProgressBar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }

    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) mProgressBar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        mProgressBar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }

    public void setListener(MyWebViewListener listener) {
        this.listener = listener;
    }

    public static class MyWebViewListener {
        //js回调本地方法
        public void onJsCallBack(String str, String action) {
        }
    }
}

