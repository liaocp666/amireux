package cn.liaocp.amireux.base.repository;

import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.domain.UserRole;
import cn.liaocp.amireux.core.repository.BaseRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/7/4
 */
public interface UserRoleRepository extends BaseRepository<UserRole> {

    @Query(value = "SELECT r FROM UserRole ur LEFT JOIN Role r ON ur.roleId = r.id WHERE  ur.userId = :userId")
    List<Role> findRoleByUserId(@Param("userId") String userId);

    @Modifying
    @Query(value = "DELETE FROM UserRole ur WHERE ur.userId = :userId")
    void deleteByUserId(@Param("userId") String userId);

    @Modifying
    @Query(value = "DELETE FROM UserRole ur WHERE ur.roleId = :roleId")
    void deleteByRoleId(@Param("roleId") String roleId);

    @Query(value = "SELECT r FROM UserRole ur LEFT JOIN Role r ON ur.roleId = r.id WHERE  ur.userId = :userId AND r.enable = :enable")
    List<Role> findRoleByUserIdAndEnable(@Param("userId") String userId, @Param("enable") Boolean enable);
}
