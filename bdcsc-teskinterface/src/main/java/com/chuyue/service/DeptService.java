package com.chuyue.service;

import com.chuyue.pojo.Dept;

import java.util.List;

public interface DeptService {


    List<Dept> findAllDept(int currIndex, int pageSize);

    int updateDeptByIds(String ids, String status);

    List<Dept> findAllDeptByPage(int page, int rows);

}
