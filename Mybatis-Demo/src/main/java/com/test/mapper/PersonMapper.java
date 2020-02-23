package com.test.mapper;

import com.test.pojo.Person;

import java.util.List;

public interface PersonMapper {

    public Person selectOne(int id);

    public List<Person> selectList();

    public List<Person> selectListByAge();

    public List<Person> selectPerson();

    public int insertPerson();

    public List<Person> orderByColumn();

    public int updateById();

    public List<Person> selectWhere();

    public int insertTrim();

    public int deleteOne();

    public int deleteForEach();







}


