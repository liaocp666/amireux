package cn.liaocp.amireux.base.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.bean.copier.CopyOptions;
import cn.liaocp.amireux.base.domain.BaseDomain;
import cn.liaocp.amireux.base.repository.BaseRepository;
import cn.liaocp.amireux.base.service.BaseService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public abstract class BaseServiceImpl<T extends BaseDomain> implements BaseService<T> {

    /**
     * Get the BaseDomainRepository interface implementation object
     *
     * @return implementation object
     */
    public abstract BaseRepository<T> getBaseDomainRepository();

    @Override
    @Transactional(rollbackFor = Exception.class)
    public T save(T domain) {
        if (StringUtils.isNotBlank(domain.getId())) {
            T dbDomain = findById(domain.getId());
            BeanUtil.copyProperties(domain, dbDomain, CopyOptions.create().setIgnoreNullValue(true));
            domain = dbDomain;
        }
        getBaseDomainRepository().save(domain);
        return domain;
    }

    @Override
    public List<T> findAll() {
        return getBaseDomainRepository().findAll(Sort.by(Sort.Direction.DESC, "createTime"));
    }

    public T findById(String id) {
        return getBaseDomainRepository().findById(id).orElse(null);
    }

    @Override
    public void deleteById(String id) {
        getBaseDomainRepository().deleteById(id);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<String> ids) {
        getBaseDomainRepository().deleteInBatch(
                ids.stream().map(e -> findById(e)).collect(Collectors.toList()));
    }

    @Override
    public Page<T> page(Example<T> example, Pageable pageable) {
        return getBaseDomainRepository().findAll(example, pageable);
    }

    @Override
    public T findOne(T t) {
        return getBaseDomainRepository().findOne(Example.of(t)).orElse(null);
    }
}
