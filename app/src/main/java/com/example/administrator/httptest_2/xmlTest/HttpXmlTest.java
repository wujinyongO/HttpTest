package com.example.administrator.httptest_2.xmlTest;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;

import com.example.administrator.httptest_2.R;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class HttpXmlTest extends Activity{

    private TextView tv;
<<<<<<< HEAD
    private Handler handler;
    private String url;
=======
    private String url="http://172.29.108.46:8080/JSONTest/girl.xml";
    private Handler handler;
>>>>>>> 82c7082fdebe7aaf24de5f513d8167458924839c

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_xml_test_layout);

        tv= (TextView) findViewById(R.id.http_xml_test_textView);
        handler=new Handler();
<<<<<<< HEAD
        url="http://172.29.108.46:8080/JSONTest/girl.xml";

        new GetXmlThread().start();

=======

        new GetXmlThread().start();
>>>>>>> 82c7082fdebe7aaf24de5f513d8167458924839c
    }

    class GetXmlThread extends Thread{

        public static final String TAG="GetXmlThread";
        @Override
        public void run() {
            try {
                HttpURLConnection httpConn= (HttpURLConnection) new URL(url).openConnection();
<<<<<<< HEAD
                httpConn.setRequestMethod("GET");
                httpConn.setReadTimeout(5000);
=======
>>>>>>> 82c7082fdebe7aaf24de5f513d8167458924839c
                InputStream in=httpConn.getInputStream();
                XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
                XmlPullParser parser=factory.newPullParser();
                parser.setInput(in,"UTF-8");
                int eventType=parser.getEventType();
                final List<Girl> list=new ArrayList<Girl>();

                Girl girl=null;
                while(eventType!=XmlPullParser.END_DOCUMENT){
<<<<<<< HEAD
                    Log.i(TAG,"while");
                    String label=parser.getName();

                    switch (eventType){
                        case XmlPullParser.START_TAG:
                            if("girl".equals(label)){
                                girl=new Girl();
                            }
                            else if("name".equals(label)){
                                girl.setName(parser.nextText());
                            }
                            else if("age".equals(label)){
                                girl.setAge(Integer.parseInt(parser.nextText()));
                            }
                            else if("school".equals(label)){
                                girl.setSchool(parser.nextText());
                            }
=======
                    Log.i(TAG,"run()->while");
                    String label=parser.getName();

                    switch (eventType) {

                        case XmlPullParser.START_TAG:
                        if ("girl".equals(label)) {
                            girl = new Girl();
                        } else if ("name".equals(label)) {
                            girl.setName(parser.nextText());
                        } else if ("age".equals(label)) {
                            girl.setAge(Integer.parseInt(parser.nextText()));
                        } else if ("school".equals(label)) {
                            girl.setSchool(parser.nextText());
                        }
>>>>>>> 82c7082fdebe7aaf24de5f513d8167458924839c
                            break;

                        case XmlPullParser.END_TAG:
                            if("girl".equals(label) && girl!=null){
<<<<<<< HEAD
                            list.add(girl);
                        }
=======
                                list.add(girl);
                            }
>>>>>>> 82c7082fdebe7aaf24de5f513d8167458924839c
                            break;

                        default:
                            break;
                    }
                    eventType=parser.next();
                }

                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(list.toString());
                    }
                });

            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }
<<<<<<< HEAD
=======


>>>>>>> 82c7082fdebe7aaf24de5f513d8167458924839c
        }
    }
}
