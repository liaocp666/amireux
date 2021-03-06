package cn.liaocp.amireux.base.jwt;

import cn.hutool.core.io.IoUtil;
import cn.liaocp.amireux.base.SecurityConstant;
import cn.liaocp.amireux.base.dto.UserDto;
import cn.liaocp.amireux.base.service.UserService;
import cn.liaocp.amireux.core.enums.RestResultEnum;
import cn.liaocp.amireux.core.http.MultiReadHttpServletRequest;
import cn.liaocp.amireux.core.util.RequestUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Chunping.Liao
 */
@Component
@RequiredArgsConstructor
public class JwtOncePerRequestFilter extends OncePerRequestFilter {

    private final UserService userService;

    private final static Logger LOGGER = LoggerFactory.getLogger(JwtOncePerRequestFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        MultiReadHttpServletRequest wrappedRequest = new MultiReadHttpServletRequest(request);
        Instant start = Instant.now();
        try {
            doFilter(wrappedRequest, response, filterChain);
        } finally {
            Instant finish = Instant.now();
            long time = Duration.between(start, finish).toMillis();
            LOGGER.info("request uri -> [{}], request params -> [{}], time -> [{}ms]", wrappedRequest.getRequestURI(), IoUtil.read(wrappedRequest.getReader()), time);
        }
    }

    protected void doFilter(MultiReadHttpServletRequest wrappedRequest, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token = RequestUtil.getParamByHeader(SecurityConstant.AUTHORIZATION, wrappedRequest);
        if (!StringUtils.isNotBlank(token)) {
            LOGGER.warn("No token found in request -> {}", wrappedRequest.getRequestURI());
            SecurityContextHolder.clearContext();
            filterChain.doFilter(wrappedRequest, response);
            return;
        }
        UserDto userDto = userService.getUserDtoByJwtToken(token.replaceAll(SecurityConstant.TOKEN_PREFIX, StringUtils.EMPTY));
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
