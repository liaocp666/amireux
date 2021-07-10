package cn.liaocp.amireux.base.service;

import cn.liaocp.amireux.base.domain.Permission;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Chunping.Liao
 */
public interface PermissionService extends BaseService<Permission> {

    Permission findByUrl(String reqUrl);

    List<Permission> findPermissionsByRoles(List<String> roles);

    Page<Permission> page(Permission permission);

    List<Permission> findPermissionsByUserId(String userId);
}
