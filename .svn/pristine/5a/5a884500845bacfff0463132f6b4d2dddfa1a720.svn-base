package com.rate.web.statistic.dto;

import com.rate.system.rate_system.utils.CodeMsg;

/**
 * @ClassName LayResult
 * @Author LiuYong
 * @Date 2019/7/2 11:57
 * @Version 1.0
 **/
public class LayResult<T> {
    private int code;

    private String msg;

    private T data;

    private T time;

    private LayResult(T data, T time) {
        this.code = 0;
        this.msg = "操作成功";
        this.data = data;
        this.time = time;
    }

    private LayResult() {
        this.code = 0;
        this.msg = "操作成功";
    }

    private LayResult(CodeMsg cm) {
        if (cm == null) {
            return;
        }
        this.code = cm.getRetCode();
        this.msg = cm.getMessage();
    }

    public static <T> LayResult<T> success(T data, T time) {
        return new LayResult(data, time);
    }

    public static <T> LayResult<T> success() {
        return new LayResult();
    }

    public static <T> LayResult<T> error(CodeMsg cm) {
        return new LayResult<>(cm);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public T getData() {
        return data;
    }

    public T getTime() {
        return time;
    }
}
