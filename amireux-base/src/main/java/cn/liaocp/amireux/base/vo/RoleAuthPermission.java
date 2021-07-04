package cn.liaocp.amireux.base.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/7/4
 */
@Data
public class RoleAuthPermission implements Serializable {

    @NotBlank(message = "Role must not be blank")
    private String roleId;

    @NotNull(message = "Permissions must not be blank")
    private List<String> permissionIds;

}
