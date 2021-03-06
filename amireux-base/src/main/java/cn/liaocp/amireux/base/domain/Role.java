package cn.liaocp.amireux.base.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Chunping.Liao
 */
@Data
@Entity
@Table(name = "system_role")
@EqualsAndHashCode(callSuper = true)
public class Role extends BaseDomain {

    private static final long serialVersionUID = -1100495815072708484L;

    private String keyword;

    @NotBlank(message = "Title must not be blank")
    @Column(nullable = false)
    private String title;

    private String remark;

    @Column(name = "is_enable", columnDefinition = "tinyint", length = 1, nullable = false)
    private Boolean enable;

    @Transient
    private List<Permission> permissions = new ArrayList<>();

    @Transient
    private List<User> users = new ArrayList<>();
}
