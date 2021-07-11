package cn.liaocp.amireux.base.repository;

import java.util.List;

import cn.liaocp.amireux.base.domain.UserDept;

/**
 * @author Chunping.Liao
 */
public interface UserDeptRepository extends BaseRepository<UserDept> {

	List<String> findDeptIdByUserId(String userId);

}
