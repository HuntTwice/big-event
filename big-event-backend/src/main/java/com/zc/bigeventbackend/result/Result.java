package com.zc.bigeventbackend.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 通用返回结果类，服务端相应的数据都会封装成此对象
 * @param <T>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result<T> {
    //编码：1成功，0和其他数字表示失败
    private int code;

    //数据
    private T data;

    //错误信息
    private String msg;

    public static <T> Result<T> error(String msg) {
        Result result = new Result();
        result.msg = msg;
        result.code = 0;
        return result;
    }


    public static <T> Result<T> success(T t){
        Result<T> result = new Result<>();
        result.data = t;
        result.code = 1;
        return result;
    }

}
