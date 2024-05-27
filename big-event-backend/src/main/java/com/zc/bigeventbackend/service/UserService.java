package com.zc.bigeventbackend.service;

import com.zc.bigeventbackend.pojo.User;

public interface UserService {

    //根据用户名查询用户
    User findByUsername(String username);

    //注册
    void register(String username, String password);

    //更新用户
    void update(User user);

    void updateAvatar(String avatarUrl);


    void updatePwd(String new_pwd);
}
