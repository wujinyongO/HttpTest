package com.example.administrator.httptest_2.HttpToLoadWebView;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cielwu on 2017/2/20.
 */

public class Http_WebView_Test extends Thread {

    public static final String TAG="Http_WebView_Test";
    String url;
    WebView webView;
    Handler handler;
    StringBuffer strBuf;
    ProgressBar pro;

    public Http_WebView_Test(String url, WebView webView, ProgressBar progressBar, Handler handler){
        this.url=url;
        this.webView=webView;
        this.handler=handler;
        this.pro=progressBar;
    }

    @Override
    public void run() {

        handler.post(new Runnable() {
            @Override
            public void run() {
                pro.setVisibility(View.VISIBLE);
            }
        });
        try {
            Log.i(TAG,"url="+url);
            URL httpUrl = new URL(url);
            HttpURLConnection httpConn = (HttpURLConnection) httpUrl.openConnection();
            httpConn.setReadTimeout(5000);
            httpConn.setRequestMethod("GET");
            BufferedReader bufInReader = new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
            String str = "";
            strBuf=new StringBuffer();
            while ((str = bufInReader.readLine()) != null) {
                strBuf.append(str);
            }
            bufInReader.close();

            Log.i(TAG,"strBuf.capacity="+strBuf.capacity());
            Log.i(TAG,"strBuf="+strBuf.toString());

            handler.post(new Runnable() {
                @Override
                public void run() {
                    webView.loadData(strBuf.toString(),"text/html; charset='gb2312'",null);
//                    webView.loadDataWithBaseURL(null,strBuf.toString(),"text/html,","GBK",null);
                    pro.setVisibility(View.GONE);
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
