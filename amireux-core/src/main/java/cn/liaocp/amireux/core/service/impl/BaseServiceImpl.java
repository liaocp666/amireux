package cn.liaocp.amireux.core.service.impl;

import cn.liaocp.amireux.core.domain.BaseDomain;
import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.core.service.BaseService;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public abstract class BaseServiceImpl<T extends BaseDomain, ID> implements BaseService<T, ID> {

    /**
     * Get the BaseDomainRepository interface implementation object
     *
     * @return implementation object
     */
    public abstract BaseRepository<T, ID> getBaseDomainRepository();

    @Override
    public T save(T domain) {
        getBaseDomainRepository().save(domain);
        return domain;
    }

}
