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

    SUCCESS(2000, "success"),
    FAIL(5000, "Request failed, please contact the system administrator"),
    USER_NOT_FOUND(5001, "Invalid username or password"),
    USER_IS_DISABLED(5002, "User is disabled"),
    INVALID_TOKEN(5003, "Invalid token");

    /**
     * Status Code
     */
    private Integer code;

    /**
     * message
     */
    private String msg;

}
