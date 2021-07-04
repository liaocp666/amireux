package cn.liaocp.amireux.base.repository;

import cn.liaocp.amireux.base.domain.Permission;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public interface PermissionRepository extends BaseRepository<Permission> {

    Permission findByUrl(String url);

    @Query("SELECT p FROM Permission p WHERE p.parentId = :parentId AND p.enable = true AND p.type <> 'api'")
    List<Permission> findMenuByParentId(@Param("parentId") String parentId);

    @Query("SELECT p FROM UserRole ur LEFT JOIN RolePermission rp ON ur.roleId = rp.roleId LEFT JOIN Permission p ON p.id = rp.permissionId WHERE ur.userId = :userId ORDER BY p.sortNum ASC, p.createTime DESC")
    List<Permission> findPermissionsByUserId(@Param("userId") String userId);
}
