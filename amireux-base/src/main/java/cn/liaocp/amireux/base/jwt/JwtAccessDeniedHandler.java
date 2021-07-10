package cn.liaocp.amireux.base.jwt;

import cn.liaocp.amireux.core.enums.RestResultEnum;
import cn.liaocp.amireux.core.util.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Chunping.Liao
 */
@Component
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) {
        ResponseUtil.writerJson(RestResultEnum.FORBIDDEN, response);
    }

}
