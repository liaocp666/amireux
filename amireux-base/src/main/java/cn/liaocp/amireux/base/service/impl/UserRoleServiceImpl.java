package cn.liaocp.amireux.base.service.impl;

import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.domain.UserRole;
import cn.liaocp.amireux.base.repository.UserRoleRepository;
import cn.liaocp.amireux.base.service.UserRoleService;
import cn.liaocp.amireux.base.vo.UserAuthRole;
import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.core.service.impl.BaseServiceImpl;
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
public class UserRoleServiceImpl extends BaseServiceImpl<UserRole> implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    @Override
    public BaseRepository<UserRole> getBaseDomainRepository() {
        return userRoleRepository;
    }

    @Override
    public List<Role> findByUserId(String userId) {
        return userRoleRepository.findRoleByUserId(userId);
    }

    @Override
    public List<Role> findByUserIdAndEnable(String userId, Boolean enable) {
        return userRoleRepository.findRoleByUserIdAndEnable(userId, enable);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void save(UserAuthRole userAuthRole) {
        userRoleRepository.deleteByUserId(userAuthRole.getUserId());
        List<UserRole> userRoles = userAuthRole.getRoleIds().stream().map(e -> {
            UserRole userRole = new UserRole();
            userRole.setUserId(userAuthRole.getUserId());
            userRole.setRoleId(e);
            return userRole;
        }).collect(Collectors.toList());
        userRoleRepository.saveAll(userRoles);
    }

    @Override
    public void deleteByUserIds(List<String> userIds) {
        userIds.forEach(e -> userRoleRepository.deleteByUserId(e));
    }

    @Override
    public void deleteByRoleIds(List<String> roleIds) {
        roleIds.forEach(e -> userRoleRepository.deleteByRoleId(e));
    }
}
