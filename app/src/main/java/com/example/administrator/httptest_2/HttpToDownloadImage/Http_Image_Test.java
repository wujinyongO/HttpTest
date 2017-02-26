package com.example.administrator.httptest_2.HttpToDownloadImage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by cielwu on 2017/2/20.
 */

public class Http_Image_Test extends Thread {

    String url;
    ImageView image;
    Handler handler;

    public Http_Image_Test(String url,ImageView image,Handler handler){
        this.url=url;
        this.image=image;
        this.handler=handler;
    }

    @Override
    public void run() {
        try {
            URL HttpUrl=new URL(url);
            HttpURLConnection HttpUrlConn= (HttpURLConnection) HttpUrl.openConnection();
            InputStream in=HttpUrlConn.getInputStream();
            BufferedInputStream bufIn=new BufferedInputStream(in);
            final Bitmap bitmap= BitmapFactory.decodeStream(bufIn);

            handler.post(new Runnable() {
                @Override
                public void run() {
                    image.setImageBitmap(bitmap);
                }
            });

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
