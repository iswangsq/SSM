package com.test.pojo;

import java.util.List;

public class Dept {

    private Integer id;
    private String name;

    private List<Emp> eList;

    public Dept() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Emp> geteList() {
        return eList;
    }

    public void seteList(List<Emp> eList) {
        this.eList = eList;
    }

    @Override
    public String toString() {
        return "dept{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", eList=" + eList +
                '}';
    }
}
