package cn.liaocp.amireux.core.config;

import cn.liaocp.amireux.core.cache.AmireuxCache;
import cn.liaocp.amireux.core.cache.AmireuxMemoryCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chunping.Liao
 */
@Configuration
public class AmireuxCacheConfig {

    @Bean
    public AmireuxCache amireuxCache() {
        return new AmireuxMemoryCache();
    }

}
