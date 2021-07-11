package cn.liaocp.amireux.base.service;

import java.util.List;

import cn.liaocp.amireux.base.domain.Dept;
import org.springframework.data.domain.Page;

/**
 * @author Chunping.Liao
 */
public interface DeptService extends BaseService<Dept> {

	List<Dept> findByUserId(String userId);

	Page<Dept> page(Dept dept);
}
