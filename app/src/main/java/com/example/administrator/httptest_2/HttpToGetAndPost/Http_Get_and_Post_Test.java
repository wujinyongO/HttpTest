package com.example.administrator.httptest_2.HttpToGetAndPost;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.administrator.httptest_2.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by cielwu on 2017/2/21.
 */

public class Http_Get_and_Post_Test extends Activity {

    EditText nameEdit,ageEdit;
    Button bt;
    Handler handler;
    ProgressBar progressBar;
    String url;
    StringBuffer strBuf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_get_post_test);

        nameEdit= (EditText) findViewById(R.id.nameEdit);
        ageEdit= (EditText) findViewById(R.id.ageEdit);
        bt= (Button) findViewById(R.id.submit);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        handler=new Handler();
        url="http://172.29.108.46:8080/test2/MyServlet";

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new MyThread(url,nameEdit.getText().toString(),ageEdit.getText().toString(),handler).start();
//                Toast.makeText(Http_Get_and_Post_Test.this,"name="+name.getText().toString()+" age="+age.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    class MyThread extends Thread{

        public static final String TAG="Http_Get_and_Post_Test";

        String url1,name1,age1;
        Handler handler1;

        public MyThread(String url,String name,String age,Handler handler){
            this.url1=url;
            this.name1=name;
            this.age1=age;
            this.handler1=handler;
        }

        @Override
        public void run() {
            doGet();
//            doPost();
        }

        private void doPost(){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.VISIBLE);
                }
            });
            try {
                Thread.sleep(1000);

                URL httpUrl=new URL(url1);
                HttpURLConnection httpConn= (HttpURLConnection) httpUrl.openConnection();
                httpConn.setRequestMethod("POST");
                httpConn.setReadTimeout(5000);
                OutputStream out=httpConn.getOutputStream();
                String content="name="+name1+"&age="+age1;
                out.write(content.getBytes());

                BufferedReader bufIn=new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                String str;
                strBuf=new StringBuffer();
                while((str=bufIn.readLine())!=null){
                    strBuf.append(str);
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler1.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Http_Get_and_Post_Test.this,strBuf.toString(),Toast.LENGTH_LONG).show();
                }
            });
        }

        private void doGet() {

            Log.i(TAG,"doGet()");
            handler1.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.VISIBLE);
                }
            });

            url1=url1+"?name="+name1+"&age="+age1;
            Log.i(TAG,"url1="+url1);

            try {
                Thread.sleep(1000);

                URL HttpUrl=new URL(url1);
                HttpURLConnection HttpConn= (HttpURLConnection) HttpUrl.openConnection();
                HttpConn.setRequestMethod("GET");
                HttpConn.setReadTimeout(5000);

                BufferedReader bufIn=new BufferedReader(new InputStreamReader(HttpConn.getInputStream()));
                String str;
                strBuf=new StringBuffer();

                while((str=bufIn.readLine())!=null){
                    strBuf.append(str);
                }
                Log.i(TAG,"strBuf.capacity()="+strBuf.capacity());
                Log.i(TAG,"strBuf.toString()="+strBuf.toString());

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            handler1.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                    Toast.makeText(Http_Get_and_Post_Test.this,strBuf.toString(),Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
