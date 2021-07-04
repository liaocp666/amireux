package cn.liaocp.amireux.base.service;

import cn.liaocp.amireux.base.domain.User;
import cn.liaocp.amireux.base.dto.UserDto;
import cn.liaocp.amireux.base.vo.UserAuthRole;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
public interface UserService extends BaseService<User> {

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

    /**
     * Get UserDto by jwt token
     *
     * @param token jwt token
     * @return UserDto
     */
    UserDto getUserDtoByJwtToken(String token);

    User findById(String id);

    UserDto findUserDtoById(String id);

    User save(User user);

    void auth(UserAuthRole userAuthRole);
}
