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
public class UserAuthRole implements Serializable {

    @NotBlank(message = "未找到用户")
    private String userId;

    @NotNull(message = "请选择角色")
    private List<String> roleIds;

}
