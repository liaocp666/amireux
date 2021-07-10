package cn.liaocp.amireux.base.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.liaocp.amireux.base.SecurityConstant;
import cn.liaocp.amireux.base.domain.Permission;
import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.domain.User;
import cn.liaocp.amireux.base.dto.UserDto;
import cn.liaocp.amireux.base.enums.PermissionTypeEnum;
import cn.liaocp.amireux.base.repository.BaseRepository;
import cn.liaocp.amireux.base.repository.UserRepository;
import cn.liaocp.amireux.base.service.*;
import cn.liaocp.amireux.base.util.SecurityUtil;
import cn.liaocp.amireux.base.util.TreeUtil;
import cn.liaocp.amireux.base.vo.UserAuthRole;
import cn.liaocp.amireux.core.cache.AmireuxCache;
import cn.liaocp.amireux.core.enums.RestResultEnum;
import cn.liaocp.amireux.core.exception.AmireuxException;
import cn.liaocp.amireux.core.properties.AmireuxProperties;
import cn.liaocp.amireux.core.properties.SecurityProperties;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Chunping.Liao
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User> implements UserService, UserDetailsService, AuthService {

    private final UserRepository userRepository;

    private final AmireuxProperties amireuxProperties;

    private final SecurityProperties securityProperties;

    private final AmireuxCache amireuxCache;

    private final PermissionService permissionService;

    private final RoleService roleService;

    private final UserRoleService userRoleService;

    private final static Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public void checkUser(User user) {
        if (!user.getEnable()) {
            throw new AmireuxException(RestResultEnum.USER_IS_DISABLED);
        }
    }

    @Override
    public BaseRepository<User> getBaseDomainRepository() {
        return userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User dbUser = Optional.ofNullable(findByUsername(username)).orElseThrow(() -> {
            throw new AmireuxException(RestResultEnum.USER_NOT_FOUND);
        });
        checkUser(dbUser);
        return org.springframework.security.core.userdetails.User.builder().username(username)
                .disabled(!dbUser.getEnable()).password(dbUser.getPassword()).build();
    }

    @Override
    public User register(User user) {
        Assert.hasText(user.getUsername(), "username must not be blank");
        Assert.hasText(user.getPassword(), "password must not be blank");
        Assert.isTrue(!existsUserByUsername(user.getUsername()), "username already exists");
        String password = SecurityUtil.encryptPassword(user.getPassword());
        user.setPassword(password);
        user.setEnable(Boolean.TRUE);
        return save(user);
    }

    @Override
    public String login(String username, String password) {
        User user = Optional.ofNullable(findByUsername(username)).orElseThrow(() -> {
            throw new AmireuxException(RestResultEnum.USER_NOT_FOUND);
        });
        if (!SecurityUtil.checkPassword(password, user.getPassword())) {
            throw new AmireuxException(RestResultEnum.USER_NOT_FOUND);
        }
        checkUser(user);
        return SecurityUtil.generateToken(user.getId(), amireuxProperties.getName(),
                DateUtil.offsetHour(new Date(), securityProperties.getTokenExpireTime()), getSecret());
    }

    @Override
    public String getSecret() {
        String secret = amireuxCache.get(SecurityConstant.SecurityCacheConstant.JWT_SECRET);
        if (StringUtils.isBlank(secret)) {
            secret = generateSecret();
        }
        return secret;
    }

    @Override
    public String generateSecret() {
        String secret;
        if (securityProperties.getSecretDynamicEnable()) {
            secret = SecurityUtil.generateSecret();
        } else {
            secret = securityProperties.getSecretKey();
        }
        amireuxCache.put(SecurityConstant.SecurityCacheConstant.JWT_SECRET, secret,
                DateUtil.offsetHour(new Date(), securityProperties.getSecretExpireTime()).getTime());
        return secret;
    }

    @Override
    public Boolean existsUserByUsername(String username) {
        return userRepository.existsUserByUsername(username);
    }

    @Override
    public UserDto getUserDtoByJwtToken(String token) {
        UserDto userDto = null;
        try {
            String userId = SecurityUtil.getSubject(token, amireuxProperties.getName(), getSecret());
            userDto = findUserDtoById(userId);
            if (ObjectUtils.isEmpty(userDto)) {
                LOGGER.warn("Not found by user, token: {}, id: {}", token, userId);
                return userDto;
            }
            checkUser(userDto.getUser());
        } catch (Exception e) {
            LOGGER.warn("Failed to get authorized user by token", e);
        }
        return userDto;
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public UserDto findUserDtoById(String id) {
        Assert.hasText(id, "user id must not be blank");
        User user = new User();
        user.setId(id);
        user.setEnable(Boolean.TRUE);
        UserDto userDto = new UserDto();
        User dbUser = findOne(user);
        Assert.notNull(dbUser, "未找到用户");
        userDto.setUser(dbUser);
        userDto.setRoles(roleService.findRolesByUserId(user.getId(), Boolean.TRUE));
        userDto.setPermissions(permissionService.findPermissionsByRoles(userDto.getRoles().stream().map(Role::getId).collect(Collectors.toList())));
        return userDto;
    }

    @Override
    public void signOut() {
        SecurityContextHolder.clearContext();
    }

    @Override
    public List<Permission> findDynamicMenu() {
        List<Permission> allPermission = permissionService.findPermissionsByUserId(SecurityUtil.currentUser().getUser().getId())
                .stream().filter(e -> !StringUtils.equals(e.getType(), PermissionTypeEnum.API.getType()))
                .collect(Collectors.toList());
        return TreeUtil.tree(allPermission);
    }

    @Override
    public User currentUser() {
        return SecurityUtil.currentUser().getUser();
    }

    @Override
    public User save(User user) {
        if (StringUtils.isNotBlank(user.getPassword()) || StringUtils.isNotBlank(user.getConfirmPassword())) {
            Assert.state(StringUtils.equals(user.getConfirmPassword(), user.getPassword()), "两次密码输入不一致！");
            user.setPassword(SecurityUtil.encryptPassword(user.getPassword()));
        }
        return super.save(user);
    }

    @Override
    public void auth(UserAuthRole userAuthRole) {
        userRoleService.save(userAuthRole);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deleteByIds(List<String> ids) {
        super.deleteByIds(ids);
        userRoleService.deleteByUserIds(ids);
    }
}
