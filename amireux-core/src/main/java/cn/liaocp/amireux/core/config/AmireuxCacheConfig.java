package cn.liaocp.amireux.core.config;

import cn.liaocp.amireux.core.cache.AmireuxCache;
import cn.liaocp.amireux.core.cache.AmireuxMemoryCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Configuration
public class AmireuxCacheConfig {

    @Bean
    public AmireuxCache amireuxCache() {
        return new AmireuxMemoryCache();
    }

}
