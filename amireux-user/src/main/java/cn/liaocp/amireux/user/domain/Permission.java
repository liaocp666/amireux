package cn.liaocp.amireux.user.domain;

import cn.liaocp.amireux.core.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * @author Chunping.Liao
 * @date 2021/5/15
 */
@Data
@Entity
@Table(name = "system_permission")
@EqualsAndHashCode(callSuper = true)
public class Permission extends BaseDomain {

	private static final long serialVersionUID = -5404991409857645267L;

	@Id
    @GeneratedValue(generator = "permissionId")
    @GenericGenerator(name = "permissionId", strategy = "uuid2")
    private String id;

    private String title;

    private String parentId;

    private String icon;

    private String remark;

    private String permissionType;

    @NotBlank(message = "Access URL must not be blank")
    @Column(unique = true)
    private String url;

    /**
     * Vue File Path
     */
    private String template;

    @Column(name = "is_enable", columnDefinition = "tinyint", length = 1, nullable = false)
    private String enable;

    private Integer sortNum;

    @JsonIgnore
    @ManyToMany(mappedBy = "permissions")
    private Set<Role> roles;
}
