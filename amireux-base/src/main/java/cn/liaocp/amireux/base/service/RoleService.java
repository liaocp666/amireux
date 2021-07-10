package cn.liaocp.amireux.base.service;

import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.vo.RoleAuthPermission;

import java.util.List;

/**
 * @author Chunping.Liao
 */
public interface RoleService extends BaseService<Role> {

    void authPermission(RoleAuthPermission roleAuthPermission);

    List<Role> findRolesByUserId(String userId);

    List<Role> findRolesByUserId(String userId, Boolean enable);
}
