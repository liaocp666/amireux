package cn.liaocp.amireux.base.service;

import cn.liaocp.amireux.base.domain.Permission;
import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.domain.RolePermission;
import cn.liaocp.amireux.core.service.BaseService;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/7/4
 */
public interface RolePermissionService extends BaseService<RolePermission> {

    List<Permission> findPermissionsByRoles(List<String> roles);

    /**
     * save role and permission
     *
     * @param roleId        roleId
     * @param permissionIds permissionIds
     */
    void save(String roleId, List<String> permissionIds);

    void deleteByRoleIds(List<String> roleIds);

    void deleteByPermission(List<String> permissionIds);
}
