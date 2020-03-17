package com.chuyue.controller;

import com.chuyue.pojo.User;
import com.chuyue.service.RedisService;
import com.chuyue.service.UserService;
import com.chuyue.utils.SysResult;
import org.apache.shiro.authc.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class LoginController {

    @Autowired
    private UserService usreService;

    @Autowired
    private RedisService redisService;

    /**
     * 根据用户id查询用户是否存在
     *
     * @param user
     * @param session
     * @return
     */
    @PostMapping("/login")
    public SysResult login(@RequestBody User user, HttpSession session) {

        boolean result = false;

        try {

            //查询用户密码和用户名是否正确
            result = usreService.findUserByAndPassword(user);

            //如果用户名的信息正确，则将sessionid存入redis 并设置失效时间

            if (result) {
                String token = session.getId();
                redisService.set(token, null, 50);
                return SysResult.ok("认证成功", "", token);
            } else {
                return SysResult.error("认证失败", "", null);
            }

        } catch (AuthenticationException e) {
            e.printStackTrace();
            return SysResult.error("认证失败", "", null);
        }

    }

}
