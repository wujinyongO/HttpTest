package com.example.administrator.httptest_2.JsonTest;

import java.util.List;

/**
 * Created by cielwu on 2017/2/22.
 */

public class Person {

    private String name;
    private int age;
    private String imageUrl;
    private List<SchoolInfo> schoolInfoList;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<SchoolInfo> getSchoolInfoList() {
        return schoolInfoList;
    }

    public void setSchoolInfoList(List<SchoolInfo> schoolInfoList) {
        this.schoolInfoList = schoolInfoList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
