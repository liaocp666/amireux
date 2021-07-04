package cn.liaocp.amireux.base.jwt;

import cn.liaocp.amireux.base.SecurityConstant;
import cn.liaocp.amireux.core.enums.RestResultEnum;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Chunping.Liao
 * @date 2021/5/30
 */
@Component
public class JwtAccessDecisionManager implements AccessDecisionManager {

    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        for (ConfigAttribute configAttribute : configAttributes) {
            String authInfo = configAttribute.getAttribute();
            if (SecurityConstant.ANONYMOUS.equals(authInfo)) {
                return;
            }
            if (SecurityConstant.FORBIDDEN.equals(authInfo)) {
                throw new AccessDeniedException(RestResultEnum.FORBIDDEN.getMsg());
            }
            Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
            for (GrantedAuthority authority : authorities) {
                // if the URL is authorized to the user
                if (authority.getAuthority().equals(authInfo)) {
                    return;
                }
            }
        }
        throw new AccessDeniedException(RestResultEnum.FORBIDDEN.getMsg());
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
