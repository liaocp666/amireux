package cn.liaocp.amireux.base.jwt;

import cn.hutool.core.util.ReUtil;
import cn.liaocp.amireux.core.properties.SecurityProperties;
import cn.liaocp.amireux.base.SecurityConstant;
import cn.liaocp.amireux.base.domain.Permission;
import cn.liaocp.amireux.base.service.PermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
import java.util.Set;

/**
 * @author Chunping.Liao
 * @date 2021/5/30
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class JwtFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private final SecurityProperties securityProperties;

    private final PermissionService permissionService;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String reqUrl = ((FilterInvocation) object).getRequest().getRequestURI();
        // Filtering whitelist URLs
        if (matchWhiteList(reqUrl)) {
            return SecurityConfig.createList(SecurityConstant.ANONYMOUS);
        }
        Permission permission = permissionService.findByUrl(reqUrl);
        if (ObjectUtils.isEmpty(permission)) {
            log.info("Permission not found -> {}", reqUrl);
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
        Set<String> whiteList = securityProperties.getWhiteList();
        for (String whiteUrl : whiteList) {
            if (ReUtil.contains(whiteUrl, reqUrl)) {
                return Boolean.TRUE;
            }
        }
        return Boolean.FALSE;
    }
}
