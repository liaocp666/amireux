package cn.liaocp.amireux.user.service;

import cn.liaocp.amireux.core.service.BaseService;
import cn.liaocp.amireux.user.domain.Permission;
import cn.liaocp.amireux.user.domain.Role;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public interface PermissionService extends BaseService<Permission, String> {

    Permission findByUrl(String reqUrl);

    List<Permission> findPermissionsByRoles(List<Role> roles);
}
