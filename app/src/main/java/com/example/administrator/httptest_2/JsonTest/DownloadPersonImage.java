package com.example.administrator.httptest_2.JsonTest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.widget.ImageView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2017/2/24.
 */

public class DownloadPersonImage extends Thread {

    private String url;
    private ImageView personImage;
    private Handler handler;

    public DownloadPersonImage(String u,ImageView image,Handler hand) {
        this.url=u;
        this.personImage=image;
        this.handler=hand;
    }

    @Override
    public void run() {
        try {
            HttpURLConnection httpConn= (HttpURLConnection) new URL(url).openConnection();
            final Bitmap bitmap= BitmapFactory.decodeStream(httpConn.getInputStream());
            handler.post(new Runnable() {
                @Override
                public void run() {
                    personImage.setImageBitmap(bitmap);
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
