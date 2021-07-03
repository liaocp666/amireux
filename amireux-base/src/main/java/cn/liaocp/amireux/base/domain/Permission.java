package cn.liaocp.amireux.base.domain;

import cn.hutool.core.util.ObjectUtil;
import cn.liaocp.amireux.core.domain.BaseDomain;
import cn.liaocp.amireux.core.domain.TreeDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.util.ObjectUtils;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/15
 */
@Data
@Entity
@Table(name = "system_permission")
@EqualsAndHashCode(callSuper = true)
@ToString(exclude = {"roles"}, callSuper = true)
public class Permission extends BaseDomain implements TreeDomain<Permission> {

    private static final long serialVersionUID = -5404991409857645267L;

    @Id
    @GeneratedValue(generator = "permissionId")
    @GenericGenerator(name = "permissionId", strategy = "uuid2")
    private String id;

    @NotBlank(message = "Permission Title must not be blank")
    private String title;

    @NotBlank(message = "Parent permission must not be blank")
    private String parentId = ROOT_NODE;

    private String icon;

    private String remark;

    /**
     * @see cn.liaocp.amireux.base.enums.PermissionTypeEnum
     */
    @NotBlank(message = "Permission Type must not be blank")
    private String permissionType;

    @NotBlank(message = "Access URL must not be blank")
    @Column(unique = true)
    private String url;

    /**
     * Vue File Path
     */
    private String template;

    @Column(name = "is_enable", columnDefinition = "tinyint", length = 1, nullable = false)
    private Boolean enable;

    private Integer sortNum;

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private List<Role> roles = new ArrayList<>();

    @Transient
    private List<Permission> children;

    @PrePersist
    void PrePersist() {
        if (ObjectUtils.isEmpty(getEnable())) {
            setEnable(Boolean.TRUE);
        }
        if (ObjectUtil.isEmpty(getSortNum())) {
            setSortNum(1);
        }
    }
}
