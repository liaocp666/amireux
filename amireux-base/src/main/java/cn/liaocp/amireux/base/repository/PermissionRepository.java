package cn.liaocp.amireux.base.repository;

import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.base.domain.Permission;
import cn.liaocp.amireux.base.domain.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public interface PermissionRepository extends BaseRepository<Permission, String> {

    Permission findByUrl(String url);

    List<Permission> findPermissionsByRolesIn(List<Role> roles);

    @Query("SELECT p FROM Permission p WHERE p.parentId = :parentId AND p.enable = true AND p.permissionType <> 'api'")
    List<Permission> findMenuByParentId(@Param("parentId") String parentId);

}
