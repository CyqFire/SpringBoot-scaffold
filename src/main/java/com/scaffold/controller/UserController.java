package com.scaffold.controller;

import com.scaffold.entity.User;
import com.scaffold.service.UserService;
import com.scaffold.util.Result;
import com.scaffold.util.TokenUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户管理接口（接单时可直接复用）
 */
@RestController
@RequestMapping("/user")
@Api(tags = "用户管理接口")
public class UserController {

    @Resource
    private UserService userService;
    @Resource
    private TokenUtil tokenUtil;

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public Result<String> login(@RequestParam String username, @RequestParam String password) {
        String token = userService.login(username, password);
        return Result.success(token);
    }

    @ApiOperation("退出登录")
    @PostMapping("/logout")
    public Result<Void> logout(@RequestParam String userId) {
        tokenUtil.logout(userId);
        return Result.success();
    }

    @ApiOperation("新增用户")
    @PostMapping("/add")
    public Result<Void> addUser(@RequestBody User user) {
        userService.save(user);
        return Result.success();
    }

    @ApiOperation("查询用户列表")
    @GetMapping("/list")
    public Result<List<User>> getUserList() {
        List<User> list = userService.list();
        return Result.success(list);
    }

    @ApiOperation("根据ID查询用户")
    @GetMapping("/{id}")
    public Result<User> getUserById(@PathVariable Long id) {
        User user = userService.getById(id);
        return Result.success(user);
    }

    @ApiOperation("修改用户")
    @PutMapping("/update")
    public Result<Void> updateUser(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }

    @ApiOperation("删除用户")
    @DeleteMapping("/{id}")
    public Result<Void> deleteUser(@PathVariable Long id) {
        userService.removeById(id);
        return Result.success();
    }
}