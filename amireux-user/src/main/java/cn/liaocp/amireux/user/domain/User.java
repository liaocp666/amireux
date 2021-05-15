package cn.liaocp.amireux.user.domain;

import cn.liaocp.amireux.core.domain.BaseDomain;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

/**
 * @author Chunping.Liao
 * @date 2021/5/15
 */
@Data
@Entity
@Table(name = "system_user")
public class User extends BaseDomain {

    @Id
    @GeneratedValue(generator = "userId")
    @GenericGenerator(name = "userId", strategy = "uuid")
    private String id;

    /**
     * login account
     */
    @Column(nullable = false, length = 8)
    private String username;

    /**
     * login password
     */
    @Column(nullable = false)
    private String password;

    private String email;

    @Column(length = 11)
    private String phone;

    @Column(name = "is_enable", columnDefinition = "tinyint", length = 1, nullable = false)
    private String enable;

    @ManyToMany
    @JoinTable(name = "system_user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Role> roles;

}
