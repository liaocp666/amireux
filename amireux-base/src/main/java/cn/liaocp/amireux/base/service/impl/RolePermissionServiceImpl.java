package cn.liaocp.amireux.base.service.impl;

import cn.liaocp.amireux.base.domain.Permission;
import cn.liaocp.amireux.base.domain.RolePermission;
import cn.liaocp.amireux.base.repository.BaseRepository;
import cn.liaocp.amireux.base.repository.RolePermissionRepository;
import cn.liaocp.amireux.base.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Chunping.Liao
 * @date 2021/7/4
 */
@Service
@RequiredArgsConstructor
public class RolePermissionServiceImpl extends BaseServiceImpl<RolePermission> implements RolePermissionService {

    private final RolePermissionRepository rolePermissionRepository;

    @Override
    public BaseRepository<RolePermission> getBaseDomainRepository() {
        return rolePermissionRepository;
    }

    @Override
    public List<Permission> findPermissionsByRoles(List<String> roles) {
        return rolePermissionRepository.findPermissionsByRoles(roles);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(String roleId, List<String> permissionIds) {
        rolePermissionRepository.deleteByRoleId(roleId);
        rolePermissionRepository.saveAll(
                permissionIds.stream().map(e -> {
                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(roleId);
                    rolePermission.setPermissionId(e);
                    return rolePermission;
                }).collect(Collectors.toList())
        );
    }

    @Override
    public void deleteByRoleIds(List<String> roleIds) {
        roleIds.forEach(e -> rolePermissionRepository.deleteByRoleId(e));
    }

    @Override
    public void deleteByPermission(List<String> permissionIds) {
        permissionIds.forEach(e -> rolePermissionRepository.deleteByPermissionId(e));
    }
}
