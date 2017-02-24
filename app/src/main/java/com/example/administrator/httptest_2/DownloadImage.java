package com.example.administrator.httptest_2;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by cielwu on 2017/2/20.
 */

public class DownloadImage extends Activity{

    String url="http://img17.3lian.com/d/file/201702/20/bf69f1af6d664f15ce8f1795c07f6391.jpg";
    ImageView image;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_image_test);

        image= (ImageView) findViewById(R.id.myImageView);
        handler=new Handler();

        new Http_Image_Test(url,image,handler).start();

    }
}
