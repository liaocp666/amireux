package cn.liaocp.amireux.user.domain;

import cn.liaocp.amireux.core.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/15
 */
@Data
@Entity
@Table(name = "system_role")
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"permissions", "users"}, callSuper = true)
public class Role extends BaseDomain {

    private static final long serialVersionUID = -1100495815072708484L;

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
    private List<Permission> permissions = new ArrayList<>();

    @ManyToMany(mappedBy = "roles")
    @JsonIgnoreProperties(value = {"roles"}, allowSetters = true)
    private List<User> users = new ArrayList<>();
}
