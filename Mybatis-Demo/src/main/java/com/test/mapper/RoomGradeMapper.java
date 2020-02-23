package com.test.mapper;

import com.test.pojo.Grade;

import java.util.List;

public interface RoomGradeMapper {

    public List<Grade> selectLeftJoin();
}
