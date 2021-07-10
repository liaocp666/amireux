package cn.liaocp.amireux.core.exception.handler;

import cn.liaocp.amireux.core.domain.RestResult;
import cn.liaocp.amireux.core.exception.AmireuxException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Chunping.Liao
 */
@Slf4j
@RestControllerAdvice
public class AmireuxExceptionHandler {

    @ExceptionHandler(AmireuxException.class)
    public RestResult runtimeException(AmireuxException e) {
        log.warn("System exception", e);
        return RestResult.fail(e.getMessage());
    }

}
