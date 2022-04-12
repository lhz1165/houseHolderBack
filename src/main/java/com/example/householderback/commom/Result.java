package com.example.householderback.commom;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.validation.BindingResult;

import java.io.Serializable;
import java.util.Objects;

/**
 * 通用返回对象
 *
 * @author abm
 */
@ApiModel("消息返回结构体")
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(
            value = "返回码",
            example = "200 成功"
    )
    public RetCode code;

    @ApiModelProperty(value = "返回消息")
    private String msg;

    @ApiModelProperty(value = "附加数据")
    public T data;



    public Result() {
    }

    public Result(RetCode code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public RetCode getCode() {
        return code;
    }

    public void setCode(RetCode code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    //操作成功
    public static <T> Result<T> succeed() {
        return succeed(RetCode.SUCCESS, "操作成功", null);
    }

    public static <T> Result<T> succeed(T data) {
        return succeed(RetCode.SUCCESS, "操作成功", data);
    }

    public static <T> Result<T> succeed(String msg, T data) {
        return succeed(RetCode.SUCCESS, msg, data);
    }

    public static <T> Result<T> succeed(RetCode code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }

    //操作失败
    public static <T> Result<T> failed() {
        return failed(RetCode.FAILED, "操作失败", null);
    }

    public static <T> Result<T> failed(String msg) {
        return failed(RetCode.FAILED, msg, null);
    }

    public static <T> Result<T> failed(RetCode code, String msg, T data) {
        return new Result<T>(code, msg, data);
    }

    /**
     * 参数验证失败使用
     *
     * @param msg 错误信息
     */
    public static <T> Result<T> validateFailed(String msg) {
        return new Result<T>(RetCode.VALIDATE_FAILED, msg, null);
    }

    public static <T> Result<T> validateFailed(BindingResult result) {
        return validateFailed(Objects.requireNonNull(result.getFieldError()).getDefaultMessage());
    }



    /**
     * 返回码枚举
     */
    public enum RetCode {
        /**
         * 操作成功
         */
        SUCCESS(200),
        /**
         * 操作失败
         */
        FAILED(500),
        /**
         * 参数校验失败
         */
        VALIDATE_FAILED(404),
        /**
         * 未认证
         */
        UNAUTHORIZED(401),
        /**
         * 未授权
         */
        FORBIDDEN(403),
        /**
         * 未知错误
         */
        UNKNOWN(-100);

        private final int code;

        RetCode(int code) {
            this.code = code;
        }

        /**
         * 自定义序列化
         */
        @JsonValue
        public int getCode() {
            return code;
        }

        /**
         * 自定义反序列化
         */
        @JsonCreator
        public static RetCode retCode(int code) {
            for (RetCode item : values()) {
                if (item.getCode() == code) {
                    return item;
                }
            }
            return null;
        }
    }



}
