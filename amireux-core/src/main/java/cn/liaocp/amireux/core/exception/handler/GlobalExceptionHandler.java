package cn.liaocp.amireux.core.exception.handler;

import cn.liaocp.amireux.core.domain.RestResult;
import cn.liaocp.amireux.core.enums.RestResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Chunping.Liao
 * @date 2021/5/17
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public RestResult runtimeException(RuntimeException e) {
        log.warn("System exception", e);
        return RestResult.fail(RestResultEnum.FAIL.getMsg());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public RestResult runtimeException(IllegalArgumentException e) {
        log.warn("System exception", e);
        return RestResult.fail(e.getMessage());
    }

}
