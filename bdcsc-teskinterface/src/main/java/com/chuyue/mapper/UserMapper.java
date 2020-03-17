package com.chuyue.mapper;

import com.chuyue.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends JpaRepository <User,String>{

    @Select("select * from user where username = #{username}")
    User findUserByUsername(String username);

    @Select("select * from user where username = #{username} and password = #{password}")
    User findUserByUsernameAndPassword(@Param("username") String username, @Param("password")String password);
}
