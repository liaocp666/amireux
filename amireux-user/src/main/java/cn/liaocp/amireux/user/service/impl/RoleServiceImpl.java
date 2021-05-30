package cn.liaocp.amireux.user.service.impl;

import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.core.service.impl.BaseServiceImpl;
import cn.liaocp.amireux.user.domain.Role;
import cn.liaocp.amireux.user.domain.User;
import cn.liaocp.amireux.user.repository.RoleRepository;
import cn.liaocp.amireux.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Lists;
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
    public List<Role> findRolesByUsers(User user) {
        return roleRepository.findRolesByUsersIn(Lists.list(user));
    }
}
