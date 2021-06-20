package cn.liaocp.amireux.user.service;

import cn.liaocp.amireux.user.domain.User;
import cn.liaocp.amireux.user.dto.DynamicMenu;

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

    /**
     * Find menu by current user
     * @return
     */
    List<DynamicMenu> findDynamicMenu();
    
    /**
	 * jwt in request to get user information
	 * 
	 * @return
	 */
	User currentUser();
}
