package cn.liaocp.amireux.base.dto;

import cn.liaocp.amireux.base.domain.Permission;
import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.domain.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/30
 */
@Data
public class UserDto implements Serializable {

    private static final long serialVersionUID = -2405739251110542062L;

    private User user;

    private List<Role> roles;

    private List<Permission> permissions;

}
