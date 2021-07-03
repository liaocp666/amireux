package cn.liaocp.amireux.base.controller;

import cn.liaocp.amireux.core.AmireuxConstant;
import cn.liaocp.amireux.core.domain.RestResult;
import cn.liaocp.amireux.base.SecurityConstant;
import cn.liaocp.amireux.base.domain.User;
import cn.liaocp.amireux.base.service.AuthService;
import cn.liaocp.amireux.base.vo.SignInParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/sign_up")
    @ApiOperation(value = "Sign up", notes = "Create a new user")
    public RestResult register(@Validated @RequestBody User user) {
        return RestResult.success(authService.register(user));
    }

    @ApiOperation(value = "Sign out")
    @PostMapping("/sign_out")
    public RestResult signOut() {
        authService.signOut();
        return RestResult.success();
    }

    @PostMapping("/sign_in")
    @ApiOperation(value = "Sign in", notes = "Use username and password to exchange for a token")
    public RestResult login(@Validated @RequestBody SignInParam signInParam) {
        return RestResult.success(SecurityConstant.TOKEN_PREFIX + authService.login(signInParam.getUsername(), signInParam.getPassword()));
    }

    @GetMapping("/menu")
    @ApiOperation(value = "Menus", notes = "Dynamic menu generation based on permissions")
    public RestResult menu() {
        return RestResult.success(authService.findDynamicMenu());
    }
    
    @GetMapping("/current")
    @ApiOperation(value = "Current User", notes = "jwt in request to get user information")
	public RestResult currentUser() {
		return RestResult.success(authService.currentUser());
	}

}
