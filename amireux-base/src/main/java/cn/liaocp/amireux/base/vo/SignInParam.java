package cn.liaocp.amireux.base.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @author Chunping.Liao
 */
@Data
public class SignInParam implements Serializable {

    private static final long serialVersionUID = -2505480159470239183L;

    @NotBlank(message = "username must not be blank")
    private String username;

    @NotBlank(message = "password must not be blank")
    private String password;

}
