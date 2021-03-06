package cn.liaocp.amireux.base.config;

import cn.liaocp.amireux.base.repository.impl.BaseRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Chunping.Liao
 */
@Configuration
@EnableJpaRepositories(basePackages = "cn.liaocp.amireux.*.repository",
        repositoryBaseClass = BaseRepositoryImpl.class)
public class RepositoryConfig {
}
