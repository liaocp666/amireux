package cn.liaocp.amireux.base.repository;

import cn.liaocp.amireux.base.domain.User;

/**
 * @author Chunping.Liao
 */
public interface UserRepository extends BaseRepository<User> {

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
}
