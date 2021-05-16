package cn.liaocp.amireux.user;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

/**
 * Load application.yml fle configuration
 *
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@ConfigurationProperties(prefix = "amireux.security")
@EnableConfigurationProperties(SecurityProperties.class)
public class SecurityProperties {
}
