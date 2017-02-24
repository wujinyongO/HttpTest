package com.example.administrator.httptest_2.JsonTest;

import java.util.List;

/**
 * Created by cielwu on 2017/2/22.
 */

public class Result {

    private List<Person> personList;
    private int result;

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }


    public List<Person> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }

}
