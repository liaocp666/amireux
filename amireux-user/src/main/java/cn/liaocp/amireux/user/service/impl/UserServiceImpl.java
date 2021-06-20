package cn.liaocp.amireux.user.service.impl;

import cn.hutool.core.date.DateUtil;
import cn.liaocp.amireux.core.cache.AmireuxCache;
import cn.liaocp.amireux.core.enums.RestResultEnum;
import cn.liaocp.amireux.core.exception.AmireuxException;
import cn.liaocp.amireux.core.properties.AmireuxProperties;
import cn.liaocp.amireux.core.properties.SecurityProperties;
import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.core.service.impl.BaseServiceImpl;
import cn.liaocp.amireux.user.SecurityConstant;
import cn.liaocp.amireux.user.domain.User;
import cn.liaocp.amireux.user.dto.DynamicMenu;
import cn.liaocp.amireux.user.dto.UserDto;
import cn.liaocp.amireux.user.repository.UserRepository;
import cn.liaocp.amireux.user.service.AuthService;
import cn.liaocp.amireux.user.service.PermissionService;
import cn.liaocp.amireux.user.service.RoleService;
import cn.liaocp.amireux.user.service.UserService;
import cn.liaocp.amireux.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User, String>
		implements UserService, UserDetailsService, AuthService {

	private final UserRepository userRepository;

	private final AmireuxProperties amireuxProperties;
	
	private final SecurityProperties securityProperties;

	private final AmireuxCache amireuxCache;

	private final PermissionService permissionService;

	private final RoleService roleService;

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
	public BaseRepository<User, String> getBaseDomainRepository() {
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
		String secret = SecurityUtil.generateSecret();
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
		UserDto userDto = new UserDto();
		User user = findById(id);
		if (ObjectUtils.isEmpty(user)) {
			return userDto;
		}
		userDto.setUser(user);
		userDto.setRoles(roleService.findRolesByUsers(List.of(user)));
		userDto.setPermissions(permissionService.findPermissionsByRoles(userDto.getRoles()));
		return userDto;
	}

	@Override
	public List<DynamicMenu> findDynamicMenu() {
		return null;
	}

	@Override
	public User currentUser() {
		return SecurityUtil.currentUser().getUser();
	}
}
