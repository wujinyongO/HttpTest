package com.example.administrator.httptest_2.MutiThreadDownload;

import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by cielwu on 2017/2/26.
 */

public class MutiThreadDownload {


    public static int NumberOfSubThread=50;
    private String url;
    private String filename="unKnow.temp";
    private Executor threadPool= Executors.newFixedThreadPool(NumberOfSubThread);
    private Handler handler;
    public static final String TAG="MutiThreadDownload";

    public MutiThreadDownload(Handler handler, String url){
        this.url = url;
        this.filename=getFilename(url);
        this.handler=handler;

        startDownload();
    }

    private void startDownload(){
        try {
            HttpURLConnection httpConn= (HttpURLConnection) new URL(url).openConnection();
            httpConn.setRequestMethod("GET");
            httpConn.setReadTimeout(5000);
            int contentLength=httpConn.getContentLength();
            int block=contentLength/NumberOfSubThread;

            //在储存卡创建所需下载的文件，然后传到子线程里面
            File parentFile= Environment.getExternalStorageDirectory();
            File fileDownload=new File(parentFile,filename);

            for(int i=0;i<NumberOfSubThread;i++){
                long start=i*block;
                long end=(i+1)*block-1;
                if(i==NumberOfSubThread-1){
                    end=contentLength;
                }
                SubThread subThread=new SubThread(fileDownload.getAbsolutePath(),start,end);
                threadPool.execute(subThread);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getFilename(String url){
        return url.substring(url.lastIndexOf("/"));
    }

    public int getNumberOfSubThread() {
        return NumberOfSubThread;
    }

    public void setNumberOfSubThread(int numberOfSubThread) {
        NumberOfSubThread = numberOfSubThread;
    }

    //////////////////////////////////////////////////////////////////////////////////
    class SubThread implements Runnable{

        private long start;
        private long end;
        private String filePath;

        public SubThread(String filePath,long start,long end){
            this.filePath=filePath;
            this.start=start;
            this.end=end;
        }

        @Override
        public void run() {
            try {


                Log.i("SubThread","run");
                HttpURLConnection httpConn= (HttpURLConnection) new URL(url).openConnection();
                httpConn.setRequestMethod("GET");
                httpConn.setReadTimeout(5000);

                //多线程下载的关键，断点续传的原理与此相同
                httpConn.setRequestProperty("Range","bytes="+start+"-"+end);//设置请求的数据的范围，以实现多线程下载
                RandomAccessFile accessFile=new RandomAccessFile(new File(filePath),"rwd");
                accessFile.seek(start);

                InputStream in=httpConn.getInputStream();
                byte buf[]=new byte[1024];
                int len;
                while((len=in.read(buf))!=-1){
                    accessFile.write(buf,0,len);
                }
                if(in!=null) {
                    in.close();
                }
                if(accessFile!=null) {
                    accessFile.close();
                }

                Message msg=new Message();
                msg.what=001;
                msg.arg1=1;
                handler.sendMessage(msg);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
