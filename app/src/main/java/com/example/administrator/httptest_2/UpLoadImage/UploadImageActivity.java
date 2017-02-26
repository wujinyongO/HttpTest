package com.example.administrator.httptest_2.UpLoadImage;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.administrator.httptest_2.R;

import java.io.File;

/**
 * error!!错误！！
 * 无法上传,原因不详
 * Created by cielwu on 2017/2/26.
 */

public class UploadImageActivity extends Activity {

    private Button bt;
    private ProgressBar progressBar;
    private Handler handler;
    private String url="http://172.29.108.46:8080/upLoadTest/upLoadServlet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload_image_layout);

        bt= (Button) findViewById(R.id.upLoadImage_Button_2);
        progressBar= (ProgressBar) findViewById(R.id.upLoad_progressBar);
        handler=new Handler();

        File file= Environment.getExternalStorageDirectory();
        final File filename=new File(file,"lianziban.jpg");

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new UploadThread(handler,url,filename.getAbsolutePath(),progressBar).start();
            }
        });


    }
}
