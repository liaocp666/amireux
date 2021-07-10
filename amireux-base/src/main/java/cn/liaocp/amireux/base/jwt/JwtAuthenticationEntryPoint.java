package cn.liaocp.amireux.base.jwt;

import cn.liaocp.amireux.core.enums.RestResultEnum;
import cn.liaocp.amireux.core.util.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Chunping.Liao
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
        ResponseUtil.writerJson(RestResultEnum.UNAUTHORIZED, response);
    }

}
