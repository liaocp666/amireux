package cn.liaocp.amireux.core.config;

import cn.liaocp.amireux.core.properties.AmireuxProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chunping.Liao
 * @date 2021/5/17
 */
@Configuration
@EnableConfigurationProperties(AmireuxProperties.class)
public class PropertiesConfig {
}
