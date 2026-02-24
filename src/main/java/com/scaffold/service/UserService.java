package com.scaffold.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.scaffold.entity.User;

public interface UserService extends IService<User> {
    // 登录方法
    String login(String username, String password);
}