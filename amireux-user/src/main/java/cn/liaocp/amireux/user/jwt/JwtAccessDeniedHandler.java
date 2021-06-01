package cn.liaocp.amireux.user.jwt;

import cn.liaocp.amireux.core.enums.RestResultEnum;
import cn.liaocp.amireux.core.util.ResponseUtil;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
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
public class JwtAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ResponseUtil.writerJson(RestResultEnum.FORBIDDEN, response);
    }

}
