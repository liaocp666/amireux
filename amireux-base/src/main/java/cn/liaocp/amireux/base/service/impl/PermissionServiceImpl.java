package cn.liaocp.amireux.base.service.impl;

import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.core.service.impl.BaseServiceImpl;
import cn.liaocp.amireux.core.util.TreeUtil;
import cn.liaocp.amireux.base.domain.Permission;
import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.repository.PermissionRepository;
import cn.liaocp.amireux.base.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends BaseServiceImpl<Permission, String> implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public BaseRepository<Permission, String> getBaseDomainRepository() {
        return permissionRepository;
    }

    @Override
    public Permission findByUrl(String url) {
        return permissionRepository.findByUrl(url);
    }

    @Override
    public List<Permission> findPermissionsByRoles(List<Role> roles) {
        return permissionRepository.findPermissionsByRolesIn(roles);
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
        PageRequest pageRequest = PageRequest.of(permission.getPageNum(), permission.getPageSize(),
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
}
