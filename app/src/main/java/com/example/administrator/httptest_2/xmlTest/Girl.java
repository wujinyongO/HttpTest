package com.example.administrator.httptest_2.xmlTest;

/**
<<<<<<< HEAD
 * Created by cielwu on 2017/2/25.
 */

public class Girl {

    private String name;
    private int age;
    private String school;
=======
 * Created by cielwu on 2017/2/24.
 */

public class Girl {
    private String name;
    private String school;
    private int age;
>>>>>>> 82c7082fdebe7aaf24de5f513d8167458924839c

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

<<<<<<< HEAD
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

=======
>>>>>>> 82c7082fdebe7aaf24de5f513d8167458924839c
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

<<<<<<< HEAD
=======
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

>>>>>>> 82c7082fdebe7aaf24de5f513d8167458924839c
    @Override
    public String toString() {
        return "[name="+name+" age="+age+" school="+school+"]";
    }
}
