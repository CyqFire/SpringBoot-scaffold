package com.scaffold.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.scaffold.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    // 继承BaseMapper后，无需写SQL即可实现CRUD
}