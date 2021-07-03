package cn.liaocp.amireux.base.service.impl;

import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.core.service.impl.BaseServiceImpl;
import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.domain.User;
import cn.liaocp.amireux.base.repository.RoleRepository;
import cn.liaocp.amireux.base.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/30
 */
@Service
@RequiredArgsConstructor
public class RoleServiceImpl extends BaseServiceImpl<Role, String> implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public BaseRepository<Role, String> getBaseDomainRepository() {
        return roleRepository;
    }

    @Override
    public List<Role> findRolesByUsers(List<User> users) {
        return roleRepository.findByUsersIn(users);
    }
}
