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
    private Handler handler;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.http_xml_test_layout);

        tv= (TextView) findViewById(R.id.http_xml_test_textView);
        handler=new Handler();
        url="http://172.29.108.46:8080/JSONTest/girl.xml";

        new GetXmlThread().start();
    }

    class GetXmlThread extends Thread{

        public static final String TAG="GetXmlThread";
        @Override
        public void run() {
            try {
                HttpURLConnection httpConn= (HttpURLConnection) new URL(url).openConnection();
//                httpConn.setRequestMethod("GET");
//                httpConn.setReadTimeout(5000);
                InputStream in=httpConn.getInputStream();
                XmlPullParserFactory factory=XmlPullParserFactory.newInstance();
                XmlPullParser parser=factory.newPullParser();
                parser.setInput(in,"UTF-8");
                int eventType=parser.getEventType();
                final List<Girl> list=new ArrayList<Girl>();

                Girl girl=null;
                while(eventType!=XmlPullParser.END_DOCUMENT){
//                    Log.i(TAG,"while");
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
                            break;

                        case XmlPullParser.END_TAG:
                            if("girl".equals(label) && girl!=null){
                                list.add(girl);
                            }
                            break;

                        default:
                            break;
                    }
                    eventType=parser.next();
                }
                in.close();
                httpConn.disconnect();

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
        }
    }
}
