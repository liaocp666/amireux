package cn.liaocp.amireux.user.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Data
public class SignInParam implements Serializable {

    @NotBlank(message = "username must not be blank")
    private String username;

    @NotBlank(message = "password must not be blank")
    private String password;

}
