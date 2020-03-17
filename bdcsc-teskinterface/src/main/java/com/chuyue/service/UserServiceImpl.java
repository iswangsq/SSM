package com.chuyue.service;

import com.chuyue.mapper.UserMapper;
import com.chuyue.pojo.User;
import com.chuyue.shrio.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.util.Password;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;


    @Transactional(rollbackFor = {RuntimeException.class,Error.class})
    @Override
    public String registerUsre(User user) {

        //判判是否有相同用户名记录 根据username
        User u = userMapper.findUserByUsername(user.getUsername());

        if(u != null) {
            throw  new RuntimeException("用户已存在");
        }

//        //生成主键userid  --uuid
//        if (isExistence) {
//            throw new RuntimeException("用户已存在");
//        }
        //生成userId
        String userId = UUID.randomUUID().toString().replaceAll("-", "");
        user.setUserId(userId);


        //密码加密
        String encodePassword = EncryptUtil.getMd5Hash(user.getPassword(),user.getUsername());
        user.setPassword(encodePassword);
        //设置创建时间和更新时间
        user.setCreateTime(new Date());
        user.setUpdateTime(user.getCreateTime());

        //保存用户
        userMapper.save(user);

        //返回用户的usreid

        return userId;
    }

    @Override
    public User getUserByUserId(String userId) {
        return userMapper.findOne(userId);
    }

    @Override
    public boolean findUserByAndPassword(User user) {

        String username = user.getUsername();
        //将密码通过加密
        String password = EncryptUtil.getMd5Hash(user.getPassword(), username);
        User u = userMapper.findUserByUsernameAndPassword(username, password);
        return u != null;
    }
}
