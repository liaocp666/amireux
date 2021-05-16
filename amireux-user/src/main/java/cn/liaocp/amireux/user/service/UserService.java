package cn.liaocp.amireux.user.service;

import cn.liaocp.amireux.core.service.BaseService;
import cn.liaocp.amireux.user.domain.User;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public interface UserService extends BaseService<User, String> {

    /**
     * Get the user by username
     *
     * @param username username
     * @return User
     */
    User findByUsername(String username);

    /**
     * Check if the user is valid
     *
     * @param user User
     */
    void checkUser(User user);

    /**
     * Query if the same username exists
     *
     * @param username username
     * @return Returns true if it already exists, false if it doesn't
     */
    Boolean existsUserByUsername(String username);

}
