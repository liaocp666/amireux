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
@Table(name = "system_permission")
public class Permission extends BaseDomain {

    @Id
    @GeneratedValue(generator = "permissionId")
    @GenericGenerator(name = "permissionId", strategy = "uuid")
    private String id;

    private String keyword;

    private String title;

    private String remark;

    /**
     * http request method
     *
     * @see org.springframework.http.HttpMethod
     */
    private String method;

    private String url;

    /**
     * React File Path
     */
    private String template;

    @Column(name = "is_enable", columnDefinition = "tinyint", length = 1, nullable = false)
    private String enable;

    @ManyToMany(mappedBy = "permissions")
    @JsonIgnore
    private Set<Role> roles;

}
