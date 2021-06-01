package cn.liaocp.amireux.user.jwt;

import cn.liaocp.amireux.core.enums.RestResultEnum;
import cn.liaocp.amireux.core.http.MultiReadHttpServletRequest;
import cn.liaocp.amireux.core.properties.AmireuxProperties;
import cn.liaocp.amireux.core.util.RequestUtil;
import cn.liaocp.amireux.user.SecurityConstant;
import cn.liaocp.amireux.user.dto.UserDto;
import cn.liaocp.amireux.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Chunping.Liao
 * @date 2021/5/30
 */
@Component
@RequiredArgsConstructor
public class JwtOncePerRequestFilter extends OncePerRequestFilter {

    private final UserService userService;

    private final static Logger LOGGER = LoggerFactory.getLogger(JwtOncePerRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);

        String token = RequestUtil.getParamByHeader(SecurityConstant.AUTHORIZATION, wrappedRequest);
        if (!StringUtils.hasText(token)) {
            LOGGER.warn("No token found in request");
            SecurityContextHolder.clearContext();
            filterChain.doFilter(wrappedRequest, response);
            return;
        }
        UserDto userDto = userService.getUserDtoByJwtToken(token.replaceAll(SecurityConstant.TOKEN_PREFIX, ""));
        if (ObjectUtils.isEmpty(userDto)) {
            SecurityContextHolder.clearContext();
            throw new BadCredentialsException(RestResultEnum.UNAUTHORIZED.getMsg());
        }
        Set<GrantedAuthority> grantedAuthorities = userDto.getPermissions().stream().map(p -> new SimpleGrantedAuthority(p.getUrl())).collect(Collectors.toSet());
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDto, token, grantedAuthorities);
        // Set authentication information
        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(wrappedRequest, response);
    }
}
