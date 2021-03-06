package cn.liaocp.amireux.base.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author Chunping.Liao
 */
@Data
public class RoleAuthPermission implements Serializable {

	private static final long serialVersionUID = 2764566630328733219L;

	@NotBlank(message = "Role must not be blank")
    private String roleId;

    @NotNull(message = "Permissions must not be blank")
    private List<String> permissionIds;

}
