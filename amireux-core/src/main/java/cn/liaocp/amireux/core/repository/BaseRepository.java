package cn.liaocp.amireux.core.repository;

import cn.liaocp.amireux.core.domain.BaseDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseDomain> extends JpaRepository<T, String> {

}
