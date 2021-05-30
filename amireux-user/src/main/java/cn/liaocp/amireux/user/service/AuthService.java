package cn.liaocp.amireux.user.service;

import cn.liaocp.amireux.user.domain.Permission;
import cn.liaocp.amireux.user.domain.User;

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

    List<Permission> menu();
}
