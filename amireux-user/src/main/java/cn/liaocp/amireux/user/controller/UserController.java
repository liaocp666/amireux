package cn.liaocp.amireux.user.controller;

import cn.liaocp.amireux.core.AmireuxConstant;
import cn.liaocp.amireux.core.domain.RestResult;
import cn.liaocp.amireux.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.API)
public class UserController {

    private final UserService userService;

    public final static String API = AmireuxConstant.API + "/user";

    @GetMapping("/current")
    public RestResult current() {
        return RestResult.success(userService.findByUsername("admin"));
    }

}
