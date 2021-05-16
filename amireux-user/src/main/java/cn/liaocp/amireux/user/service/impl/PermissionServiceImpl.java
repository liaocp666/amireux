package cn.liaocp.amireux.user.service.impl;

import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.core.service.impl.BaseServiceImpl;
import cn.liaocp.amireux.user.domain.Permission;
import cn.liaocp.amireux.user.repository.PermissionRepository;
import cn.liaocp.amireux.user.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Service
@RequiredArgsConstructor
public class PermissionServiceImpl extends BaseServiceImpl<Permission, String> implements PermissionService {

    private final PermissionRepository permissionRepository;

    @Override
    public BaseRepository getBaseDomainRepository() {
        return permissionRepository;
    }
}
