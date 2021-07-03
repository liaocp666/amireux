package cn.liaocp.amireux.base.config;

import cn.liaocp.amireux.base.util.SecurityUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * @author Chunping.Liao
 * @date 2021/7/2
 */
@Configuration
@EnableJpaAuditing
public class AuditorAwareConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtil.currentUser().getUser().getId());
    }
}
