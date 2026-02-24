package com.scaffold.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("sys_user") // 表名（达梦/MySQL通用）
public class User implements Serializable {
    @TableId(type = IdType.AUTO)
    private Long id;         // 主键
    private String username;// 用户名
    private String password;// 密码（实际项目需加密）
    private String phone;   // 手机号
    private Date createTime;// 创建时间
}