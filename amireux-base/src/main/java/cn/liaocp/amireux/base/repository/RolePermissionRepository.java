package cn.liaocp.amireux.base.repository;

import cn.liaocp.amireux.base.domain.Permission;
import cn.liaocp.amireux.base.domain.RolePermission;
import cn.liaocp.amireux.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/7/4
 */
public interface RolePermissionRepository extends BaseRepository<RolePermission> {

    @Query("SELECT p FROM RolePermission rp LEFT JOIN Permission p ON rp.permissionId = p.id WHERE rp.roleId IN :roleIds")
    List<Permission> findPermissionsByRoles(@Param("roleIds") List<String> roleIds);

    @Modifying
    @Query("DELETE FROM RolePermission rp where rp.roleId = :roleId")
    void deleteByRoleId(@Param("roleId") String roleId);

    @Modifying
    @Query("DELETE FROM RolePermission rp where rp.permissionId = :permissionId")
    void deleteByPermissionId(@Param("permissionId") String permissionId);
}
