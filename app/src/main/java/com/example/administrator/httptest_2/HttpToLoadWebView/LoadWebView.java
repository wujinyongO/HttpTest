package com.example.administrator.httptest_2.HttpToLoadWebView;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.example.administrator.httptest_2.R;

/**
 * Created by cielwu on 2017/2/20.
 */

public class LoadWebView extends Activity {

    WebView webview;
    String url="";
    Handler handler;
    ProgressBar pro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_webview_test);

        webview= (WebView) findViewById(R.id.myWebView);
        pro= (ProgressBar) findViewById(R.id.progressBar2);
        handler=new Handler();
        url="http://123.207.111.88/";

        new Http_WebView_Test(url,webview,pro,handler).start();
//        webview.loadUrl("https://www.baidu.com/");

    }
}
