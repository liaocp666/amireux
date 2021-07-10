package cn.liaocp.amireux.core.util;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

/**
 * @author Chunping.Liao
 */
public class RequestUtil {

    /**
     * Get HttpServletRequest in Spring Context
     *
     * @return HttpServletResponse
     */
    public static HttpServletRequest getRequest() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Objects.requireNonNull(servletRequestAttributes).getRequest();
    }

    public static String getParamByHeader(String name) {
        HttpServletRequest request = getRequest();
        return request.getHeader(name);
    }

    public static String getParamByHeader(String name, HttpServletRequest request) {
        return request.getHeader(name);
    }

}
