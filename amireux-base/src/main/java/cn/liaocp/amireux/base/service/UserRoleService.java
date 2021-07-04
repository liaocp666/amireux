package cn.liaocp.amireux.base.service;

import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.domain.UserRole;
import cn.liaocp.amireux.base.vo.UserAuthRole;
import cn.liaocp.amireux.core.service.BaseService;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/7/4
 */
public interface UserRoleService extends BaseService<UserRole> {

    List<Role> findByUserId(String userId);

    List<Role> findByUserIdAndEnable(String userId, Boolean enable);

    void save(UserAuthRole userAuthRole);

    void deleteByUserIds(List<String> userIds);

    void deleteByRoleIds(List<String> roleIds);
}
