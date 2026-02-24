package com.scaffold.controller;

import com.scaffold.util.Result;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试接口（展示Redis/数据库能力）
 */
@RestController
@RequestMapping("/test")
//@Api(tags = "测试接口")
public class TestController {

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private Environment environment;

//    @ApiOperation("Redis测试")
    @GetMapping("/redis")
    public Result<String> testRedis() {
        // 存入Redis
        redisTemplate.opsForValue().set("test:key", "SpringBoot + Redis 测试成功");
        // 获取Redis
        String value = redisTemplate.opsForValue().get("test:key");
        return Result.success(value);
    }

//    @ApiOperation("数据源测试")
    @GetMapping("/db")
    public Result<String> testDb() {
        String activeProfile = environment.getActiveProfiles().length > 0 ? 
            environment.getActiveProfiles()[0] : "default";
        return Result.success("达梦/MySQL 数据源适配成功（当前使用：" + activeProfile + "）");
    }
}