package cn.liaocp.amireux.base.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Chunping.Liao
 */
@Data
@Entity
@Table(name = "system_user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseDomain {

    private static final long serialVersionUID = -2782201728914530440L;

    private String avatar;

    /**
     * login account
     */
    @Column(nullable = false, unique = true)
    @NotBlank(message = "username must not be blank")
    private String username;

    /**
     * login password
     */
    @Column(nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    private String email;

    @Column(length = 11)
    private String phone;

    @Column(name = "is_enable", columnDefinition = "tinyint", length = 1, nullable = false)
    private Boolean enable;

    @Transient
    private List<Role> roles = new ArrayList<>();

    @Transient
    private List<Dept> depts = new ArrayList<>();

}
