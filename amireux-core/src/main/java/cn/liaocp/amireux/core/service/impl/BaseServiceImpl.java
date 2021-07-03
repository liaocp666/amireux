package cn.liaocp.amireux.core.service.impl;

import cn.liaocp.amireux.core.domain.BaseDomain;
import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.core.service.BaseService;
import org.springframework.data.domain.*;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public abstract class BaseServiceImpl<T extends BaseDomain, ID extends Serializable> implements BaseService<T, ID> {

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

    @Override
    public List<T> findAll() {
        return getBaseDomainRepository().findAll(Sort.by(Sort.Direction.DESC, "createTime"));
    }

    @Override
    public Page<T> page(Example<T> example, Pageable pageable) {
        return getBaseDomainRepository().findAll(example, pageable);
    }
}
