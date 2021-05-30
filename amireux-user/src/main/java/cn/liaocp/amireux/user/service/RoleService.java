package cn.liaocp.amireux.user.service;

import cn.liaocp.amireux.core.service.BaseService;
import cn.liaocp.amireux.user.domain.Role;
import cn.liaocp.amireux.user.domain.User;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/30
 */
public interface RoleService extends BaseService<Role, String> {

    List<Role> findRolesByUsers(User user);

}
