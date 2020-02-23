package com.test.mapper;

import com.test.pojo.Dept;
import com.test.pojo.Emp;

import java.util.List;

public interface DeptEmpMapper {

    public List<Dept> selectForDept();

    public List<Emp> selectForEmp();

}
