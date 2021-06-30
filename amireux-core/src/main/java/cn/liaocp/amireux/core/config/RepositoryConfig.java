package cn.liaocp.amireux.core.config;

import cn.liaocp.amireux.core.repository.impl.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Chunping.Liao
 * @date 2021/6/30
 */
@Configuration
@EnableJpaRepositories(basePackages = "cn.liaocp.amireux.*.repository",
        repositoryBaseClass = BaseRepositoryImpl.class)
public class RepositoryConfig {
}
