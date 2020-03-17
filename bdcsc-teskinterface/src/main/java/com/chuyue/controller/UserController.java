package com.chuyue.controller;

import com.chuyue.pojo.BaseEntity;
import com.chuyue.pojo.User;
import com.chuyue.service.UserService;
import com.chuyue.utils.SysResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/restful")
public class UserController extends BaseEntity {

    static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;
    //    {
//        "username":"zhansan",
//        "password":"123123",
//        "state": 0,
//        "dept_id":"100100"
//    }
    @PostMapping("/user")
    public SysResult registerUsre(@RequestBody @Valid User user, BindingResult bindingResult) {

        StringBuilder stringBuilder = new StringBuilder();
        if(bindingResult.hasErrors()){
            for (ObjectError error : bindingResult.getAllErrors()) {
                stringBuilder.append(error.getDefaultMessage()).append(";");
            }
            logger.error("参数校验错误:" + stringBuilder.toString());
            return SysResult.error("参数校验错误","",stringBuilder.toString());
        }

        String userId = null;
        try {
            userId = userService.registerUsre(user);
            return SysResult.ok("用户注册成功","", userId);

        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.error("用户注册失败", "", e.getMessage());
        }
    }

    @GetMapping("/user/{userId}")
    public SysResult getUserByUserId(@PathVariable(value = "userId",required = true) String userId) {

        User user = null;
        try {
            user = userService.getUserByUserId(userId);
            return SysResult.ok("用户信息","", user);
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.error("出现异常","", user);
        }

    }
}
