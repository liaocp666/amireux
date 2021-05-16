package cn.liaocp.amireux.user.service;

import cn.liaocp.amireux.user.domain.User;

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

}
