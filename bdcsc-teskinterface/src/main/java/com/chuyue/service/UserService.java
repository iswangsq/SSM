package com.chuyue.service;

import com.chuyue.pojo.User;

public interface UserService {

    String registerUsre(User user);

    User getUserByUserId(String userId);

    boolean findUserByAndPassword(User user);
}
