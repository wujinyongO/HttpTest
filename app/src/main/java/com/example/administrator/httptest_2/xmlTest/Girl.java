package com.example.administrator.httptest_2.xmlTest;

/**
 * Created by cielwu on 2017/2/24.
 */

public class Girl {
    private String name;
    private String school;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "[name="+name+" age="+age+" school="+school+"]";
    }
}
