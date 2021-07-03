package cn.liaocp.amireux.base.config;

import cn.liaocp.amireux.base.jwt.*;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@EnableWebSecurity
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 未登录访问处理
     */
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    /**
     * 未授权处理
     */
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    /**
     * 访问鉴权 - 认证token、签名
     */
    private final JwtOncePerRequestFilter jwtOncePerRequestFilter;

    /**
     * 认证权限处理
     */
    private final JwtAccessDecisionManager jwtAccessDecisionManager;

    /**
     * 获取访问url所需要的角色信息
     */
    private final JwtFilterInvocationSecurityMetadataSource jwtFilterInvocationSecurityMetadataSource;

    /**
     * 密码加密对象
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // 关闭 CSRF 攻击
                .csrf().disable()
                // TODO认证失败处理类
                .exceptionHandling().authenticationEntryPoint(null).and()
                // 关闭 session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 过滤请求
                .authorizeRequests()
                // 登录接口和验证码匿名访问
                .antMatchers("/captchaImage").permitAll()
                // 其他URL都需要认证
                .anyRequest().authenticated()
                .and()
                .headers().frameOptions().disable();
        httpSecurity.logout().logoutUrl("/logout");

        httpSecurity.authorizeRequests().withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
            @Override
            public <O extends FilterSecurityInterceptor> O postProcess(O object) {
                object.setSecurityMetadataSource(jwtFilterInvocationSecurityMetadataSource);
                object.setAccessDecisionManager(jwtAccessDecisionManager);
                return object;
            }
        });

        httpSecurity.addFilterBefore(jwtOncePerRequestFilter, BasicAuthenticationFilter.class);

        httpSecurity.exceptionHandling().authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler);
    }

}
