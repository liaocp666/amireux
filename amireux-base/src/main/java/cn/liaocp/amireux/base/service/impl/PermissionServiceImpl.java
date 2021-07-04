package cn.liaocp.amireux.base.service.impl;

import cn.liaocp.amireux.base.service.RolePermissionService;
import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.core.service.impl.BaseServiceImpl;
import cn.liaocp.amireux.core.util.TreeUtil;
import cn.liaocp.amireux.base.domain.Permission;
import cn.liaocp.amireux.base.repository.PermissionRepository;
import cn.liaocp.amireux.base.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Lists;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends BaseServiceImpl<Permission> implements PermissionService {

    private final PermissionRepository permissionRepository;

    private final RolePermissionService rolePermissionService;

    @Override
    public BaseRepository<Permission> getBaseDomainRepository() {
        return permissionRepository;
    }

    @Override
    public Permission findByUrl(String url) {
        return permissionRepository.findByUrl(url);
    }

    @Override
    public List<Permission> findPermissionsByRoles(List<String> roles) {
        return rolePermissionService.findPermissionsByRoles(roles);
    }

    @Override
    public List<Permission> findMenuByParentId(String parentId) {
        return permissionRepository.findMenuByParentId(parentId);
    }

    @Override
    public Page<Permission> page(Permission permission) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("parentId", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains());
        PageRequest pageRequest = PageRequest.of(permission.getPageNo(), permission.getPageSize(),
                Sort.by(Sort.Order.asc("sortNum"), Sort.Order.asc("createTime")));
        Page<Permission> page = super.page(Example.of(permission, matcher), pageRequest);
        List<Permission> all = findAll();
        page.getContent().forEach(e -> TreeUtil.childrenTree(e, all));
        return page;
    }

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll(Sort.by(Sort.Order.asc("sortNum"), Sort.Order.desc("createTime")));
    }

    @Override
    public List<Permission> findPermissionsByUserId(String userId) {
        return permissionRepository.findPermissionsByUserId(userId);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteById(String id) {
        permissionRepository.deleteById(id);
        rolePermissionService.deleteByPermission(Lists.list(id));
    }
}
