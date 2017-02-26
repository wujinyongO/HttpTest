package com.example.administrator.httptest_2.HttpClientTest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.example.administrator.httptest_2.R;

/**
 * Created by cielwu on 2017/2/22.
 */

public class HttpClientTest extends Activity {

    EditText nameEdit,ageEdit;
    Button bt;
    ProgressBar pro;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_get_post_test);

        nameEdit= (EditText) findViewById(R.id.nameEdit);
        ageEdit= (EditText) findViewById(R.id.ageEdit);
        bt= (Button) findViewById(R.id.submit);
        pro= (ProgressBar) findViewById(R.id.progressBar);
        handler=new Handler();

        new MyHttpClient(nameEdit.getText().toString(),ageEdit.getText().toString()).start();

    }

    class MyHttpClient extends Thread{

        String name,age;

        public MyHttpClient(String name,String age){
            this.name=name;
            this.age=age;
        }

        private void doHttpClientGet(){

        }
    }
}
