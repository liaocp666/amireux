package cn.liaocp.amireux.core.repository.impl;

import cn.liaocp.amireux.core.domain.BaseDomain;
import cn.liaocp.amireux.core.repository.BaseRepository;
import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import java.io.Serializable;

/**
 * @author Chunping.Liao
 * @date 2021/6/22
 */
public class BaseRepositoryImpl<T extends BaseDomain, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    private final EntityManager entityManager;

    public BaseRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

}
