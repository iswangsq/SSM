package com.test.pojo;

import java.io.InputStream;

public class Grade {

    private Integer id;

    private String name;

    private  Room room;

    public Grade() {

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

    public Room getRomm() {
        return room;
    }

    public void setRomm(Room room) {
        this.room = room;
    }

    @Override
    public String toString() {
        return "Grade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", room=" + room +
                '}';
    }
}
