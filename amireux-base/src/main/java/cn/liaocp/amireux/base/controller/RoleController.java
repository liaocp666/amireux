package cn.liaocp.amireux.base.controller;

import cn.liaocp.amireux.base.domain.Role;
import cn.liaocp.amireux.base.service.RoleService;
import cn.liaocp.amireux.base.vo.RoleAuthPermission;
import cn.liaocp.amireux.core.AmireuxConstant;
import cn.liaocp.amireux.core.domain.RestResult;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author Chunping.Liao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(RoleController.API)
public class RoleController {

    public final static String API = AmireuxConstant.API + "/role";

    public final RoleService roleService;

    @GetMapping("/page")
    public RestResult page(Role role) {
        ExampleMatcher matcher = ExampleMatcher.matching()
                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains());
        PageRequest pageRequest = PageRequest.of(role.getPageNo(), role.getPageSize(),
                Sort.by(Sort.Order.desc("createTime")));
        return RestResult.success(roleService.page(Example.of(role, matcher), pageRequest));
    }

    @PostMapping("/save")
    public RestResult save(@Valid @RequestBody Role role) {
        role = roleService.save(role);
        return RestResult.success(role);
    }

    @GetMapping("/detail")
    public RestResult detail(@RequestParam(value = "id") String id) {
        return RestResult.success(roleService.findAllById(id));
    }

    @PostMapping("/delete")
    public RestResult delete(@RequestBody List<String> ids) {
        roleService.deleteByIds(ids);
        return RestResult.success();
    }

    @PostMapping("/auth")
    public RestResult authPermission(@Valid @RequestBody RoleAuthPermission roleAuthPermission) {
        roleService.authPermission(roleAuthPermission);
        return RestResult.success();
    }

    @GetMapping("/queryByUser")
    public RestResult findByUser(@RequestParam(value = "userId") String userId) {
        return RestResult.success(roleService.findRolesByUserId(userId));
    }

}
