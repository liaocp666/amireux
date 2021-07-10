package cn.liaocp.amireux.base.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

/**
 * @author Chunping.Liao
 */
@Data
@Entity
@Table(name = "system_user_role")
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseDomain {

	private static final long serialVersionUID = -6471352070109296800L;

	@Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String roleId;

}
