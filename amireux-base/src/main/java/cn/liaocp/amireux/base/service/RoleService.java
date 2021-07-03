package cn.liaocp.amireux.base.service;

import cn.liaocp.amireux.core.service.BaseService;
import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.domain.User;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/30
 */
public interface RoleService extends BaseService<Role, String> {

    List<Role> findRolesByUsers(List<User> users);

}
