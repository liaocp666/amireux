package cn.liaocp.amireux.base.service.impl;

import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.repository.BaseRepository;
import cn.liaocp.amireux.base.repository.RoleRepository;
import cn.liaocp.amireux.base.service.RolePermissionService;
import cn.liaocp.amireux.base.service.RoleService;
import cn.liaocp.amireux.base.service.UserRoleService;
import cn.liaocp.amireux.base.vo.RoleAuthPermission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chunping.Liao
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl<Role> implements RoleService {

    private final RoleRepository roleRepository;

    private final UserRoleService userRoleService;

    private final RolePermissionService rolePermissionService;

    @Override
    public BaseRepository<Role> getBaseDomainRepository() {
        return roleRepository;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void authPermission(RoleAuthPermission roleAuthPermission) {
        rolePermissionService.save(roleAuthPermission.getRoleId(), roleAuthPermission.getPermissionIds());
    }

    @Override
    public List<Role> findRolesByUserId(String userId) {
        return userRoleService.findByUserId(userId);
    }

    @Override
    public List<Role> findRolesByUserId(String userId, Boolean enable) {
        return userRoleService.findByUserIdAndEnable(userId, enable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<String> ids) {
        super.deleteByIds(ids);
        rolePermissionService.deleteByRoleIds(ids);
        userRoleService.deleteByRoleIds(ids);
    }
}
