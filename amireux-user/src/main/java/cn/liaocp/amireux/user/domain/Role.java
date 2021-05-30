package cn.liaocp.amireux.user.domain;

import cn.liaocp.amireux.core.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "system_role_permission",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "id"))
    @ApiModelProperty(hidden = true)
    @JsonIgnore
    private Set<Permission> permissions;

    @JsonIgnore
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;
}
