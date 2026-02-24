package com.scaffold.util;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Token生成+Redis存储（体现Redis使用能力）
 */
@Component
public class TokenUtil {
    // 密钥（可自定义）
    private static final String SECRET = "scaffold-secret-2026";
    // Token有效期：2小时
    private static final long EXPIRE_TIME = 2 * 60 * 60 * 1000L;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    // 生成Token
    public String generateToken(String userId) {
        // 1. 生成JWT Token
        String token = Jwts.builder()
                .setSubject(userId)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRE_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
        // 2. 存入Redis（防止Token伪造，便于退出登录）
        redisTemplate.opsForValue().set("TOKEN:" + userId, token, EXPIRE_TIME, TimeUnit.MILLISECONDS);
        return token;
    }

    // 验证Token
    public boolean validateToken(String userId, String token) {
        if (StrUtil.isBlank(userId) || StrUtil.isBlank(token)) {
            return false;
        }
        // 从Redis获取Token对比
        String redisToken = redisTemplate.opsForValue().get("TOKEN:" + userId);
        return token.equals(redisToken);
    }

    // 退出登录（删除Redis Token）
    public void logout(String userId) {
        redisTemplate.delete("TOKEN:" + userId);
    }
}