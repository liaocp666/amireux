package cn.liaocp.amireux.base.domain;

import cn.liaocp.amireux.core.domain.BaseDomain;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

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
@Table(name = "system_user")
@EqualsAndHashCode(callSuper = true)
public class User extends BaseDomain {

	private static final long serialVersionUID = -2782201728914530440L;

	@Id
    @GeneratedValue(generator = "userId")
    @GenericGenerator(name = "userId", strategy = "uuid2")
    private String id;

	private String avatar;

    /**
     * login account
     */
    @Column(nullable = false, unique = true)
    @NotBlank(message = "username must not be blank")
    private String username;

    /**
     * login password
     */
    @Column(nullable = false)
    private String password;

    @Transient
    private String confirmPassword;

    private String email;

    @Column(length = 11)
    private String phone;

    @Column(name = "is_enable", columnDefinition = "tinyint", length = 1, nullable = false)
    private Boolean enable;

    @Transient
    private List<Role> roles = new ArrayList<>();
}
