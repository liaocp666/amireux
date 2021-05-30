package cn.liaocp.amireux.user.jwt;

import cn.liaocp.amireux.core.properties.AmireuxProperties;
import cn.liaocp.amireux.user.SecurityConstant;
import cn.liaocp.amireux.user.domain.Permission;
import cn.liaocp.amireux.user.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;

/**
 * @author Chunping.Liao
 * @date 2021/5/30
 */
@Component
@RequiredArgsConstructor
public class JwtFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final AmireuxProperties amireuxProperties;

    private final PermissionService permissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String reqUrl = ((FilterInvocation) object).getRequest().getRequestURI();
        // Filtering whitelist URLs
        if (matchWhiteList(reqUrl)) {
            return Collections.EMPTY_LIST;
        }
        Permission permission = permissionService.findByUrl(reqUrl);
        if (ObjectUtils.isEmpty(permission)) {
            return SecurityConfig.createList(SecurityConstant.FORBIDDEN);
        }
        return SecurityConfig.createList(permission.getUrl());
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }

    private Boolean matchWhiteList(String reqUrl) {
        Set<String> whiteList = amireuxProperties.getSecurity().getWhiteList();
        for (String s : whiteList) {
            if (s.indexOf(reqUrl) == 1) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
