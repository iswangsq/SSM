package com.chuyue.mapper;

import com.chuyue.pojo.Dept;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeptMapper {

    List<Dept> findAllDept();

    @Update("update dept_p set state = #{status} where dept_id = #{id}")
    void updateDeptByIds(@Param("id") String id, @Param("status") String status);

    @Select("select * from dept_p limit #{currIndex},#{pageSize}")
    List<Dept> findAllDeptByLimit(@Param("currIndex") int currIndex, @Param("pageSize") int pageSize);
}
