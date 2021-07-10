package cn.liaocp.amireux.base.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Chunping.Liao
 */
@Data
@Entity
@Table(name = "system_role_permission")
@EqualsAndHashCode(callSuper = true)
public class RolePermission extends BaseDomain {

	private static final long serialVersionUID = 7338630545763945384L;

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
