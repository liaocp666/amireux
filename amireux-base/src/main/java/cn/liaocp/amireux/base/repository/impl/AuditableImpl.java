package cn.liaocp.amireux.base.repository.impl;

import cn.liaocp.amireux.base.util.SecurityUtil;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author Chunping.Liao
 */
public class AuditableImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(SecurityUtil.currentUser().getUser().getId());
    }

}