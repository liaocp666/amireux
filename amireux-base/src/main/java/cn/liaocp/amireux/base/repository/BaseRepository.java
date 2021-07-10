package cn.liaocp.amireux.base.repository;

import cn.liaocp.amireux.base.domain.BaseDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/**
 * @author Chunping.Liao
 */
@NoRepositoryBean
public interface BaseRepository<T extends BaseDomain> extends JpaRepository<T, String> {

}
