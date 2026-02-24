package com.scaffold.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scaffold.entity.User;
import com.scaffold.mapper.UserMapper;
import com.scaffold.service.UserService;
import com.scaffold.util.TokenUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    private TokenUtil tokenUtil;

    @Override
    public String login(String username, String password) {
        // 1. 查询用户
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(User::getUsername, username)
                    .eq(User::getPassword, password); // 实际项目需加密对比
        User user = this.getOne(queryWrapper);
        
        // 2. 验证用户
        if (user == null) {
            throw new RuntimeException("用户名或密码错误");
        }
        
        // 3. 生成Token
        return tokenUtil.generateToken(user.getId().toString());
    }
}