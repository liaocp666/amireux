package cn.liaocp.amireux.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Getter
@AllArgsConstructor
public enum RestResultEnum {

    SUCCESS(2000, "成功"),
    FAIL(5000, "请求失败，请联系管理员！"),
    USER_NOT_FOUND(5001, "账号或密码错误"),
    USER_IS_DISABLED(5002, "用户被禁用"),
    UNAUTHORIZED(5003, "无效令牌"),
    FORBIDDEN(4003, "禁止访问");


    /**
     * Status Code
     */
    private Integer code;

    /**
     * message
     */
    private String msg;

}
