package cn.liaocp.amireux.base.domain;

import cn.liaocp.amireux.core.domain.BaseDomain;
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
@Table(name = "system_user_role")
@EqualsAndHashCode(callSuper = true)
public class UserRole extends BaseDomain {

    @Id
    @GeneratedValue(generator = "permissionId")
    @GenericGenerator(name = "permissionId", strategy = "uuid2")
    private String id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String roleId;

}
