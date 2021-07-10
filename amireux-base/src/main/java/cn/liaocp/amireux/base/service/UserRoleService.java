package cn.liaocp.amireux.base.service;

import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.domain.UserRole;
import cn.liaocp.amireux.base.vo.UserAuthRole;

import java.util.List;

/**
 * @author Chunping.Liao
 */
public interface UserRoleService extends BaseService<UserRole> {

    List<Role> findByUserId(String userId);

    List<Role> findByUserIdAndEnable(String userId, Boolean enable);

    void save(UserAuthRole userAuthRole);

    void deleteByUserIds(List<String> userIds);

    void deleteByRoleIds(List<String> roleIds);
}
