package cn.liaocp.amireux.base.repository.impl;

import cn.liaocp.amireux.base.domain.BaseDomain;
import cn.liaocp.amireux.base.repository.BaseRepository;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;

/**
 * @author Chunping.Liao
 */
public class BaseRepositoryImpl<T extends BaseDomain> extends SimpleJpaRepository<T, String> implements BaseRepository<T> {

    private final EntityManager entityManager;

    BaseRepositoryImpl(JpaEntityInformation entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.entityManager = entityManager;
    }

    @Override
    public <S extends T> S save(S entity) {
        return super.save(entity);
    }
}
