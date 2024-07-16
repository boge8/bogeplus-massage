package com.bogeplus.common.util;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ApiModel(value = "返回结果", description = "返回结果")
public class Result<T> {
    @ApiModelProperty(value = "返回信息", example = "操作成功")
    private String msg;
    @ApiModelProperty(value = "状态码 200：成功 500：失败", example = "200")
    private Integer code; // 状态码 200：成功 500：失败
    @ApiModelProperty(value = "返回数据", example = "{user:zhangsan}")
    private T data; // 返回数据

    public static <T> Result success(String msg, Integer code, T data) {
        Result<T> tResult = new Result<>();
        tResult.setCode(code);
        tResult.setMsg(msg);
        tResult.setData(data);
        return tResult;
    }    public static <T> Result success(String msg, Integer code) {
        Result<T> tResult = new Result<>();
        tResult.setCode(code);
        tResult.setMsg(msg);
        return tResult;
    }

    public static <T> Result success(String msg, T data) {
        Result<T> tResult = new Result<>();
        tResult.setCode(200);
        tResult.setMsg(msg);
        tResult.setData(data);
        return tResult;
    }

    public static <T> Result success(T data) {
        Result<T> tResult = new Result<>();
        tResult.setCode(200);
        tResult.setMsg("操作成功");
        tResult.setData(data);
        return tResult;
    }

    public static <T> Result success() {
        Result<T> tResult = new Result<>();
        tResult.setCode(200);
        tResult.setMsg("操作成功");
        return tResult;
    }
    public static <T> Result faild(String msg, Integer code, T data) {
        Result<T> tResult = new Result<>();
        tResult.setCode(code);
        tResult.setMsg(msg);
        tResult.setData(data);
        return tResult;
    }    public static <T> Result faild(String msg, Integer code) {
        Result<T> tResult = new Result<>();
        tResult.setCode(code);
        tResult.setMsg(msg);
        return tResult;
    }

    public static <T> Result faild(String msg, T data) {
        Result<T> tResult = new Result<>();
        tResult.setCode(500);
        tResult.setMsg(msg);
        tResult.setData(data);
        return tResult;
    }

    public static <T> Result faild(T data) {
        Result<T> tResult = new Result<>();
        tResult.setCode(500);
        tResult.setMsg("操作成功");
        tResult.setData(data);
        return tResult;
    }

    public static <T> Result faild() {
        Result<T> tResult = new Result<>();
        tResult.setCode(500);
        tResult.setMsg("操作成功");
        return tResult;
    }



}
