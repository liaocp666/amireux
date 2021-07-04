package cn.liaocp.amireux.core.domain;

import cn.liaocp.amireux.core.enums.RestResultEnum;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestResult implements Serializable {

    private static final long serialVersionUID = 8656683122046098605L;

    /**
     * Status Code
     */
    private Integer code;

    /**
     * message
     */
    private String msg;

    /**
     * data
     */
    private Object data;

    /**
     * processed successfully RestResult
     *
     * @return processed successfully RestResult
     */
    public static RestResult success() {
        RestResult restResult = new RestResult();
        restResult.setCode(RestResultEnum.SUCCESS.getCode());
        restResult.setMsg(RestResultEnum.SUCCESS.getMsg());
        return restResult;
    }

    /**
     * RestResult with returned data and processed successfully
     *
     * @return processed successfully RestResult
     */
    public static RestResult success(Object data) {
        RestResult restResult = success();
        restResult.setData(data);
        return restResult;
    }

    /**
     * Returns data with a failure message
     *
     * @param msg failure message
     * @return RestResult
     */
    public static RestResult fail(String msg) {
        RestResult restResult = new RestResult();
        restResult.setCode(RestResultEnum.FAIL.getCode());
        restResult.setMsg(msg);
        return restResult;
    }

}
