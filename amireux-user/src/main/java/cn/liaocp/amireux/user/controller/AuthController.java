package cn.liaocp.amireux.user.controller;

import cn.liaocp.amireux.core.AmireuxConstant;
import cn.liaocp.amireux.core.domain.RestResult;
import cn.liaocp.amireux.user.domain.User;
import cn.liaocp.amireux.user.service.AuthService;
import cn.liaocp.amireux.user.vo.LoginParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(AuthController.API)
public class AuthController {

    public final static String API = AmireuxConstant.API + "/auth";

    private final AuthService authService;

    @PostMapping("/register")
    @ApiOperation(value = "Register", notes = "Register a new user")
    public RestResult register(@Validated @RequestBody User user) {
        return RestResult.success(authService.register(user));
    }

    @PostMapping("/login")
    @ApiOperation(value = "Login", notes = "Use username and password to exchange for a token")
    public RestResult login(@Validated @RequestBody LoginParam loginParam) {
        return RestResult.success(authService.login(loginParam.getUsername(), loginParam.getPassword()));
    }

}
