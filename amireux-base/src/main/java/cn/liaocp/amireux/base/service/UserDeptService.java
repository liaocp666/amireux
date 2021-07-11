package cn.liaocp.amireux.base.service;

import java.util.List;

import cn.liaocp.amireux.base.domain.UserDept;

/**
 * @author Chunping.Liao
 */
public interface UserDeptService extends BaseService<UserDept> {

	List<String> findDeptIdByUserId(String userId);

}
