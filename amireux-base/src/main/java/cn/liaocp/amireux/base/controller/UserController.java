package cn.liaocp.amireux.base.controller;

import cn.liaocp.amireux.base.domain.User;
import cn.liaocp.amireux.base.service.UserService;
import cn.liaocp.amireux.base.vo.UserAuthRole;
import cn.liaocp.amireux.core.AmireuxConstant;
import cn.liaocp.amireux.core.domain.RestResult;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/6/20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.API)
public class UserController {

    public final static String API = AmireuxConstant.API + "/user";

    private final UserService userService;

    @GetMapping("/page")
    public RestResult page(User user) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("username", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("phone", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains());
        PageRequest pageRequest = PageRequest.of(user.getPageNo(), user.getPageSize(),
                Sort.by(Sort.Order.desc("createTime"), Sort.Order.desc("enable")));
        return RestResult.success(userService.page(Example.of(user, matcher), pageRequest));
    }

    @PostMapping("/save")
    public RestResult save(@RequestBody @Valid User user) {
        return RestResult.success(userService.save(user));
    }

    @PostMapping("/delete")
    public RestResult delete(@RequestBody List<String> ids) {
        userService.deleteByIds(ids);
        return RestResult.success();
    }

    @GetMapping("/detail")
    public RestResult detail(@RequestParam("id") String id) {
        User user = userService.detail(id);
        user.setPassword(StringUtils.EMPTY);
        return RestResult.success(user);
    }

    @PostMapping("/auth")
    public RestResult auth(@RequestBody @Valid UserAuthRole userAuthRole) {
        userService.auth(userAuthRole);
        return RestResult.success();
    }

}
