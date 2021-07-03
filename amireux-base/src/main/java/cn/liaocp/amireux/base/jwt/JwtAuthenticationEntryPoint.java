package cn.liaocp.amireux.base.jwt;

import cn.liaocp.amireux.core.enums.RestResultEnum;
import cn.liaocp.amireux.core.util.ResponseUtil;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Chunping.Liao
 * @date 2021/5/30
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ResponseUtil.writerJson(RestResultEnum.UNAUTHORIZED, response);
    }

}
