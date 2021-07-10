package cn.liaocp.amireux.base.config;

import cn.liaocp.amireux.base.repository.impl.AuditableImpl;
import cn.liaocp.amireux.base.repository.impl.BaseRepositoryImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Chunping.Liao
 */
@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(repositoryBaseClass = BaseRepositoryImpl.class)
public class DataJpaConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditableImpl();
    }

}
