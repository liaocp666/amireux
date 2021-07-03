package cn.liaocp.amireux.base.repository;

import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.domain.User;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/30
 */
public interface RoleRepository extends BaseRepository<Role, String> {

    List<Role> findByUsersIn(List<User> users);

}
