package com.example.administrator.httptest_2.JsonTest;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.httptest_2.R;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by cielwu on 2017/2/22.
 */

public class JSONTest extends Activity {

    public static final String TAG="JSONTest";
    Button bt;
    ListView personListView;
    String url="http://172.29.108.46:8080/JSONTest/TestJsonServlet";
    List<Person> personList;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_test_layout);

        bt= (Button) findViewById(R.id.json_test_button_2);
        personListView= (ListView) findViewById(R.id.personListView);
        handler=new Handler();

        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SendJsonData(personListView,JSONTest.this).start();
            }
        });
    }

    class SendJsonData extends Thread{

        private ListView ThreadListView;
        private Context ThreadContext;

        public SendJsonData(ListView threadListView,Context context) {
            this.ThreadListView = threadListView;
            this.ThreadContext=context;
        }

        @Override
        public void run() {
            try {
                URL httpUrl=new URL(url);
                HttpURLConnection httpConn= (HttpURLConnection) httpUrl.openConnection();
                httpConn.setRequestMethod("GET");
                BufferedReader bufRead=new BufferedReader(new InputStreamReader(httpConn.getInputStream()));
                String str;
                StringBuffer sb=new StringBuffer();
                while((str=bufRead.readLine())!=null){
                    sb.append(str);
                }

                personList=parseJson(sb.toString());
                Gson gson=new Gson();
                Log.i(TAG,gson.toJson(personList));

                if(personList!=null) {
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            personAdapter myAdapter = new personAdapter(personList, ThreadContext);
                            ThreadListView.setAdapter(myAdapter);
                        }
                    });
                }

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Nullable
        private List<Person> parseJson(String json){
            try {
                JSONObject result=new JSONObject(json);
                Log.i(TAG,"result="+result.getInt("result"));

                if(result.getInt("result")==1){
                    JSONArray personArray=result.getJSONArray("personList");
                    List<Person> personList=new ArrayList<Person>();

                    for(int i=0;i<personArray.length();i++){
                        Person person=new Person();
                        JSONObject personObject=personArray.getJSONObject(i);

                        String name=personObject.getString("name");
                        int age=personObject.getInt("age");
                        String imageUrl=personObject.getString("imageUrl");
                        Log.i(TAG,"name="+name+" age="+age+" imageUrl="+imageUrl);

                        person.setName(name);
                        person.setAge(age);
                        person.setImageUrl(imageUrl);

                        JSONArray schoolArray=personObject.getJSONArray("schoolInfoList");
                        List<SchoolInfo> schoolInfoList=new ArrayList<SchoolInfo>();
                        for(int j=0;j<schoolArray.length();j++){
                            SchoolInfo schoolInfo=new SchoolInfo();
                            JSONObject schoolInfoObject=schoolArray.getJSONObject(j);
                            String school=schoolInfoObject.getString("school");
                            Log.i(TAG,"school="+school);

                            schoolInfo.setSchool(school);
                            schoolInfoList.add(schoolInfo);
                        }

                        person.setSchoolInfoList(schoolInfoList);
                        personList.add(person);
                    }

                    return personList;
                }
                else{
                    Log.e(TAG,"result.getresult()!=1");
                    return null;
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        private void createResult(){
        /*Result result=new Result();
        result.setResult(1);
        List<Person> PersonList=new ArrayList<Person>();

        Person person1,person2;
        person1=new Person();
        person1.setName("tom");
        person1.setAge(11);
        person1.setImageUrl("http://img17.3lian.com/d/file/201702/20/bf69f1af6d664f15ce8f1795c07f6391.jpg");
        List<SchoolInfo> schoolInfoList1=new ArrayList<SchoolInfo>();
        SchoolInfo schoolInfo1,schoolInfo2;
        schoolInfo1=new SchoolInfo();
        schoolInfo1.setSchool("清华");
        schoolInfo2=new SchoolInfo();
        schoolInfo2.setSchool("北大");
        schoolInfoList1.add(schoolInfo1);
        schoolInfoList1.add(schoolInfo2);
        person1.setSchoolInfoList(schoolInfoList1);

        person2=new Person();
        person2.setName("ciel");
        person2.setAge(22);
        person2.setImageUrl("http://img17.3lian.com/d/file/201702/20/bf69f1af6d664f15ce8f1795c07f6391.jpg");
        List<SchoolInfo> schoolInfoList2=new ArrayList<SchoolInfo>();
        SchoolInfo schoolInfo3,schoolInfo4;
        schoolInfo3=new SchoolInfo();
        schoolInfo3.setSchool("浙大");
        schoolInfo4=new SchoolInfo();
        schoolInfo4.setSchool("中大");
        schoolInfoList2.add(schoolInfo3);
        schoolInfoList2.add(schoolInfo4);
        person2.setSchoolInfoList(schoolInfoList2);

        PersonList.add(person1);
        PersonList.add(person2);
        result.setPersonList(PersonList);

        Gson gson=new Gson();
        Log.i(TAG,"gson.toJson="+gson.toJson(result));*/
        }
    }

    class personAdapter extends BaseAdapter{

        public static final String TAG="personAdapter";
        private List<Person> personList_2;
        private Context context;
        private LayoutInflater inflater;

        public personAdapter(List<Person> person,Context context){
            this.personList_2=person;
            this.context=context;
            inflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            if(personList_2==null)
                Log.i(TAG,"personList_2=null");
            else
                Log.i(TAG,"getCount()->personList.size()="+personList_2.size());

            return personList_2.size();
        }

        @Override
        public Object getItem(int position) {
            return personList_2.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder=null;
            if(convertView==null){
                convertView=inflater.inflate(R.layout.personlistview_item,null);
                viewHolder=new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            }else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            Person person=personList_2.get(position);
            viewHolder.name.setText("name: "+person.getName());
            viewHolder.age.setText("age: "+person.getAge());
            List<SchoolInfo> schoolInfoList=new ArrayList<SchoolInfo>();
            schoolInfoList=person.getSchoolInfoList();
            viewHolder.school1.setText("school1: "+schoolInfoList.get(0).getSchool());
            viewHolder.school2.setText("school2: "+schoolInfoList.get(1).getSchool());

            //download image
            Handler handler=new Handler();
            new DownloadPersonImage(person.getImageUrl(),viewHolder.personImage,handler).start();

            return convertView;
        }

        class ViewHolder{
            ImageView personImage;
            TextView name;
            TextView age;
            TextView school1;
            TextView school2;

            public ViewHolder(View view){
                personImage= (ImageView) view.findViewById(R.id.person_image);
                name= (TextView) view.findViewById(R.id.json_test_name);
                age= (TextView) view.findViewById(R.id.json_test_age);
                school1= (TextView) view.findViewById(R.id.json_test_school1);
                school2= (TextView) view.findViewById(R.id.json_test_school2);
            }

        }
    }
}
