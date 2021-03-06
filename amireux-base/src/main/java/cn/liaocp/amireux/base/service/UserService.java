package cn.liaocp.amireux.base.service;

import cn.liaocp.amireux.base.domain.User;
import cn.liaocp.amireux.base.dto.UserDto;
import cn.liaocp.amireux.base.vo.UserAuthRole;

/**
 * @author Chunping.Liao
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

    User findAllById(String id);

    UserDto findUserDtoById(String id);

    User save(User user);

    void auth(UserAuthRole userAuthRole);

    /**
     * 获取用户详细信息
     *
     * @param id 用户id
     * @return 用户信息
     */
    User detail(String id);
}
