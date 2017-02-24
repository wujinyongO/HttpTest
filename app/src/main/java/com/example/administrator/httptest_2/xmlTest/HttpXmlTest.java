package com.example.administrator.httptest_2.xmlTest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.administrator.httptest_2.R;

/**
 * Created by Administrator on 2017/2/24.
 */

public class HttpXmlTest extends Activity{

    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_xml_test_layout);

        tv= (TextView) findViewById(R.id.http_xml_test_textView);
    }
}
