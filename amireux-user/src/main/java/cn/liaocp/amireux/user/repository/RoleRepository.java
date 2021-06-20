package cn.liaocp.amireux.user.repository;

import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.user.domain.Role;
import cn.liaocp.amireux.user.domain.User;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/30
 */
public interface RoleRepository extends BaseRepository<Role, String> {

    List<Role> findByUsersIn(List<User> users);

}
