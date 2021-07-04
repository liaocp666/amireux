package cn.liaocp.amireux.base.service;

import cn.liaocp.amireux.base.domain.Permission;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public interface PermissionService extends BaseService<Permission> {

    Permission findByUrl(String reqUrl);

    List<Permission> findPermissionsByRoles(List<String> roles);

    List<Permission> findMenuByParentId(String parentId);

    Page<Permission> page(Permission permission);

    List<Permission> findPermissionsByUserId(String userId);
}
