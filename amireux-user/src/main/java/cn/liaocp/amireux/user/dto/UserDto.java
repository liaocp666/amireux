package cn.liaocp.amireux.user.dto;

import cn.liaocp.amireux.user.domain.Permission;
import cn.liaocp.amireux.user.domain.Role;
import cn.liaocp.amireux.user.domain.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/30
 */
@Data
public class UserDto implements Serializable {

    private User user;

    private List<Role> roles;

    private List<Permission> permissions;

}
