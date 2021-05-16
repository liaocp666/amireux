package cn.liaocp.amireux.core.service;

import cn.liaocp.amireux.core.domain.BaseDomain;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public interface BaseService<T extends BaseDomain, ID> {

    /**
     * Save or update the domain by id
     *
     * @param domain domain
     * @return domain
     */
    T save(T domain);

}
