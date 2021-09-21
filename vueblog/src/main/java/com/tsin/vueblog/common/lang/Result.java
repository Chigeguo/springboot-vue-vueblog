package com.tsin.vueblog.common.lang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author tsin
 * @since 2021/9/12-15:21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Result {

    private Integer code;
    private String msg;
    private Object data;

    public static Result success(Object data) {
        return success(200, "操作成功", data);
    }

    public static Result success(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result fail(Integer code, String msg, Object data) {
        return new Result(code, msg, data);
    }

    public static Result fail(String msg,Object data) {
        return fail(400, msg, data);
    }
    public static Result fail(String msg) {
        return fail(400, msg, null);
    }
}
