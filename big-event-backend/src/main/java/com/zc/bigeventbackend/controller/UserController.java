package com.zc.bigeventbackend.controller;

import com.zc.bigeventbackend.pojo.User;
import com.zc.bigeventbackend.result.Result;
import com.zc.bigeventbackend.service.UserService;
import com.zc.bigeventbackend.utils.JwtUtil;
import com.zc.bigeventbackend.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/user")
@CrossOrigin
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 注册接口
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {

        log.info("=========================访问注册接口==========================");
        //通过用户名查询用户
        User u = userService.findByUsername(username);
        //若用户名存在，直接返回
        if (u != null)
            return Result.error("该用户名已存在");
        //若用户名不存在，注册
        userService.register(username, password);
        return Result.success("注册成功");
    }


    /**
     * 用户登录接口
     *
     * @param username 用户名
     * @param password 密码
     * @return
     * @Pattern注解用于对参数进行合法性校验 "^\\S{5,16}$"表示传入的字符串要是5~16位非空字符
     */
    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
        //通过用户名去数据库查询是否有该用户
        User u = userService.findByUsername(username);
        //如果查不到用户，直接返回“用户名错误”
        if (u == null)
            return Result.error("用户名错误");
        //如果不为空，进行密码比对
        //数据库存入的密码是进行md5加密之后的
        //所以要先对传入的密码进行MD5加密之后再进行比较
        //DigestUtils.md5DigestAsHex(password.getBytes())就是进行md5加密
        if (DigestUtils.md5DigestAsHex(password.getBytes()).equals(u.getPassword())) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", u.getId());
            claims.put("username", u.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("密码错误");
    }

    @GetMapping
    public Result<User> userInfo() {
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    @PutMapping
    public Result update(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success("修改成功");
    }

    @PatchMapping
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success("修改头像成功");
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
        //1.校验参数
        String old_pwd = params.get("old_pwd"); //原密码
        String new_pwd = params.get("new_pwd"); //新密码
        String re_pwd = params.get("re_pwd"); //重复新密码
        //看三个密码是否有缺失
        if (!StringUtils.hasLength(old_pwd) || !StringUtils.hasLength(new_pwd) || !StringUtils.hasLength(re_pwd))
            return Result.error("缺少必要的参数");

        //查看原密码是否正确
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User loginUser = userService.findByUsername(username);

        if (!loginUser.getPassword().equals(DigestUtils.md5DigestAsHex(old_pwd.getBytes())))
            return Result.error("原密码错误");
        //看两次输入的新密码是否一致
        if (!new_pwd.equals(re_pwd))
            return Result.error("两次输入密码不一致");

        //调用service完成密码更新
        userService.updatePwd(new_pwd);
        return Result.success("更新成功");
    }
}
