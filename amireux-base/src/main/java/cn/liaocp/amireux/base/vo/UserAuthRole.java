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
public class UserAuthRole implements Serializable {

	private static final long serialVersionUID = 7691598307418407384L;

	@NotBlank(message = "未找到用户")
    private String userId;

    @NotNull(message = "请选择角色")
    private List<String> roleIds;

}
