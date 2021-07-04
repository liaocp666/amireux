package cn.liaocp.amireux.base.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author Chunping.Liao
 * @date 2021/7/4
 */
@Data
@Entity
@Table(name = "system_role_permission")
@EqualsAndHashCode(callSuper = true)
public class RolePermission extends BaseDomain {

    @Id
    @GeneratedValue(generator = "permissionId")
    @GenericGenerator(name = "permissionId", strategy = "uuid2")
    private String id;

    /**
     * @see Role#getId()
     */
    @Column(nullable = false)
    private String roleId;

    /**
     * @see Permission#getIcon()
     */
    @Column(nullable = false)
    private String permissionId;

}
