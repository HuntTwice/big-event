package com.zc.bigeventbackend.mapper;

import com.zc.bigeventbackend.pojo.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Insert("insert into user(username,password,create_time,update_time) values(#{username},#{password},now(),now())")
    int add(@Param("username") String username, @Param("password") String password);


    int update(User user);

    void updateAvatar(@Param("avatarUrl") String avatarUrl, @Param("id") Integer id);

    @Update("update user set password = #{new_pwd} where id = #{id} ;")
    void updatePwd(@Param("id") Integer id, @Param("new_pwd") String new_pwd);
}
