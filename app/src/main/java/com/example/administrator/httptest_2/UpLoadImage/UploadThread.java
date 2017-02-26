package com.example.administrator.httptest_2.UpLoadImage;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by cielwu on 2017/2/26.
 */

public class UploadThread extends Thread {

    public static final String TAG="UploadThread";
    private Handler handler;
    private String url;
    private String fileABSname;
    private ProgressBar progressBar;

    public UploadThread(Handler handler, String url, String fileABSname, ProgressBar progressBar) {
        this.handler = handler;
        this.url = url;
        this.fileABSname = fileABSname;
        this.progressBar=progressBar;
    }

    @Override
    public void run() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        String boundary="----WebKitFormBoundaryujmXjHfV1R1BP8IQ";
        String prefix="--";
        String end="\r\n";

        try {
            HttpURLConnection httpConn= (HttpURLConnection) new URL(url).openConnection();
            httpConn.setRequestMethod("POST");
//            httpConn.setReadTimeout(5000);
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);

            httpConn.setRequestProperty("Content-Type","multipart/form-data;boundary="+boundary);
            DataOutputStream out=new DataOutputStream(httpConn.getOutputStream());

            out.writeBytes(prefix+boundary+end);
            out.writeBytes("Content-Disposition:form-data;name=\"file\";filename=\"lianziban.jpg\""+end);
//            out.write("Content-Type: image/jpeg".getBytes());

            if(out!=null)
                Log.i(TAG,"out!=null");
            else
                Log.i(TAG,"out!=null");



            Log.i(TAG,"fileABSname="+fileABSname);
            FileInputStream fileInput=new FileInputStream(new File(fileABSname));
            byte buf[]=new byte[1024*2];
            int len;
            while((len=fileInput.read(buf))!=-1){
                out.write(buf,0,len);
            }
            out.writeBytes(end);
            out.writeBytes(prefix+boundary+prefix+end);
            out.flush();
            out.close();

            BufferedReader bufReader=new BufferedReader(new InputStreamReader(httpConn.getInputStream()));

            if(bufReader!=null)
                Log.i(TAG,"bufReader!=null");
            else
                Log.i(TAG,"bufReader!=null");

            StringBuffer sb=new StringBuffer();
            String str="";
            while((str=bufReader.readLine())!=null){
                sb.append(str);
            }

            bufReader.close();

            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.GONE);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
