package com.zc.bigeventbackend.service.impl;

import com.zc.bigeventbackend.mapper.UserMapper;
import com.zc.bigeventbackend.pojo.User;
import com.zc.bigeventbackend.service.UserService;
import com.zc.bigeventbackend.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        //密码进行md5加密
        password = DigestUtils.md5DigestAsHex(password.getBytes());

        //把加密的密码存入数据库
        userMapper.add(username,password);
    }

    @Override
    public void update(User user) {
//        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String new_pwd) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        //对明文密码进行md5加密后存入数据库
        new_pwd = DigestUtils.md5DigestAsHex(new_pwd.getBytes());
        userMapper.updatePwd(id,new_pwd);
    }
}
