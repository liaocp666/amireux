package cn.liaocp.amireux.core.exception.handler;

import cn.liaocp.amireux.core.domain.RestResult;
import cn.liaocp.amireux.core.enums.RestResultEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chunping.Liao
 * @date 2021/5/17
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({RuntimeException.class, Exception.class})
    public RestResult runtimeException(Exception e) {
        log.warn("System exception", e);
        return RestResult.fail(RestResultEnum.FAIL.getMsg());
    }

    @ExceptionHandler({IllegalArgumentException.class, IllegalStateException.class})
    public RestResult illegalArgumentException(Exception e) {
        log.warn("System exception", e);
        return RestResult.fail(e.getMessage());
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public RestResult methodArgumentNotValidException(MethodArgumentNotValidException e) {
        List<String> errors = e.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        return RestResult.fail(String.join(",", errors));
    }

}
