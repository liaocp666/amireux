package cn.liaocp.amireux.user.domain;

import cn.liaocp.amireux.core.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "system_role")
public class Role extends BaseDomain {

    @Id
    @GeneratedValue(generator = "roleId")
    @GenericGenerator(name = "roleId", strategy = "uuid2")
    private String id;

    private String keyword;

    private String title;

    private String remark;

    @Column(name = "is_enable", columnDefinition = "tinyint", length = 1, nullable = false)
    private String enable;

    @ManyToMany(mappedBy = "roles")
    @JsonIgnore
    private Set<User> users;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "system_role_permission",
            joinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Set<Permission> permissions;

}
