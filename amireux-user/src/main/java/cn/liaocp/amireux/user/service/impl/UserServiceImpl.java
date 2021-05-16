package cn.liaocp.amireux.user.service.impl;

import cn.liaocp.amireux.core.enums.RestResultEnum;
import cn.liaocp.amireux.core.exception.AmireuxException;
import cn.liaocp.amireux.core.repository.BaseRepository;
import cn.liaocp.amireux.core.service.impl.BaseServiceImpl;
import cn.liaocp.amireux.user.domain.User;
import cn.liaocp.amireux.user.repository.UserRepository;
import cn.liaocp.amireux.user.service.AuthService;
import cn.liaocp.amireux.user.service.UserService;
import cn.liaocp.amireux.user.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends BaseServiceImpl<User, String> implements UserService, UserDetailsService, AuthService {

    private final UserRepository userRepository;

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
    public BaseRepository getBaseDomainRepository() {
        return userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User dbUser = Optional.ofNullable(findByUsername(username)).orElseThrow(() -> {
            throw new AmireuxException(RestResultEnum.USER_NOT_FOUND);
        });
        checkUser(dbUser);
        return org.springframework.security.core.userdetails.User.builder()
                .username(username)
                .disabled(!dbUser.getEnable())
                .password(dbUser.getPassword())
                .build();
    }

    @Override
    public User register(User user) {
        return null;
    }

    @Override
    public String login(String username, String password) {
        String encryptedPassword = SecurityUtil.encryptPassword(password);
        if (!StringUtils.equals(password, encryptedPassword)) {
            throw new AmireuxException(RestResultEnum.USER_NOT_FOUND);
        }
        User user = findByUsername(username);
        checkUser(user);
        return null;
    }
}
