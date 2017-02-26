package com.example.administrator.httptest_2.MutiThreadDownload;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.httptest_2.R;

/**
 * Created by cielwu on 2017/2/25.
 */

public class MutiThreadDownloadActivity extends Activity{

    private Button bt;
    private ProgressBar progressBar;
    private String url="http://172.29.108.46:8080/JSONTest/lianziban.jpg";
    private int count=0;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //5个子线程同时下载
            if(msg.what==001){
                count+=msg.arg1;
                if(count==MutiThreadDownload.NumberOfSubThread){
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(MutiThreadDownloadActivity.this,"Download finish",Toast.LENGTH_SHORT).show();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mutidownload_layout);

        bt= (Button) findViewById(R.id.mutidownload_button_2);
        progressBar= (ProgressBar) findViewById(R.id.mutidownload_progressBar);

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                progressBar.setVisibility(View.VISIBLE);
                            }
                        });
                        new MutiThreadDownload(handler,url);
                    }
                }).start();
            }
        });

    }
}
