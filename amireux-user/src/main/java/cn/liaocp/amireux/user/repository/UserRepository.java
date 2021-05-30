package cn.liaocp.amireux.user.repository;

import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.user.domain.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public interface UserRepository extends BaseRepository<User, String> {

    /**
     * Get the user by username
     *
     * @param username username
     * @return User
     */
    User findByUsername(String username);

    /**
     * Query if the same username exists
     *
     * @param username username
     * @return Returns true if it already exists, false if it doesn't
     */
    Boolean existsUserByUsername(String username);

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
    User findWithRolesByUsername(@Param("username") String username);

    @Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.id = :id")
    User findWithRolesById(@Param("id") String id);
}
