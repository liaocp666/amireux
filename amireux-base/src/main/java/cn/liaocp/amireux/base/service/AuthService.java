package cn.liaocp.amireux.base.service;

import cn.liaocp.amireux.base.domain.Permission;
import cn.liaocp.amireux.base.domain.User;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public interface AuthService {

    /**
     * Register user
     *
     * @param user User
     * @return User
     */
    User register(User user);

    /**
     * Log in
     *
     * @param username username
     * @param password password
     * @return token
     */
    String login(String username, String password);

    /**
     * sign out
     */
    void signOut();

    /**
     * generate Secret in the cache
     *
     * @return secret
     */
    String generateSecret();

    /**
     * Get Secret in the cache
     *
     * @return secret
     */
    String getSecret();

    /**
     * Find menu by current user
     *
     * @return
     */
    List<Permission> findDynamicMenu();

    /**
     * jwt in request to get user information
     *
     * @return
     */
    User currentUser();
}
