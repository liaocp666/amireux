package cn.liaocp.amireux.user.repository;

import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.user.domain.Permission;
import cn.liaocp.amireux.user.domain.Role;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public interface PermissionRepository extends BaseRepository<Permission, String> {

    Permission findByUrl(String url);

    List<Permission> findPermissionsByRolesIn(List<Role> roles);

}
