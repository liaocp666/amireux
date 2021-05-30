package cn.liaocp.amireux.user.controller;

import cn.liaocp.amireux.core.AmireuxConstant;
import cn.liaocp.amireux.core.domain.RestResult;
import cn.liaocp.amireux.user.dto.UserDto;
import cn.liaocp.amireux.user.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

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

    @ApiOperation("Get current authorized user information")
    @GetMapping("/info")
    public RestResult currentUser() {
        UserDto user = userService.findUserDtoById("cfb31219-a39b-4b1e-aede-600b2ad61a52");
        return RestResult.success(user);
    }

    @GetMapping("/menu")
    public RestResult queryMenuByUser() {
        List<Map<String, Object>> result = new LinkedList<>();
        Map<String, Object> map = new HashMap<>();
        result.add(map);
        return RestResult.success(result);
    }

}
