package com.scaffold.util;

import lombok.Data;

/**
 * 统一返回格式（接单项目必做，提升专业性）
 */
@Data
public class Result<T> {
    private int code;       // 状态码：200成功，500失败
    private String msg;     // 提示信息
    private T data;         // 返回数据

    // 成功（无数据）
    public static <T> Result<T> success() {
        return new Result<>(200, "操作成功", null);
    }

    // 成功（有数据）
    public static <T> Result<T> success(T data) {
        return new Result<>(200, "操作成功", data);
    }

    // 失败
    public static <T> Result<T> error(String msg) {
        return new Result<>(500, msg, null);
    }

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}