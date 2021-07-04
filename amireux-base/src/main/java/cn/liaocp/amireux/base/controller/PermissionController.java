package cn.liaocp.amireux.base.controller;

import cn.liaocp.amireux.core.AmireuxConstant;
import cn.liaocp.amireux.core.domain.RestResult;
import cn.liaocp.amireux.base.domain.Permission;
import cn.liaocp.amireux.base.service.PermissionService;
import lombok.RequiredArgsConstructor;
import org.assertj.core.util.Lists;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(PermissionController.API)
public class PermissionController {

    public final static String API = AmireuxConstant.API + "/permission";

    public final PermissionService permissionService;

    @PostMapping("/save")
    public RestResult save(@Valid @RequestBody Permission permission) {
        permissionService.save(permission);
        return RestResult.success(permission);
    }

    @GetMapping("/page")
    public RestResult page(Permission permission) {
        return RestResult.success(permissionService.page(permission));
    }

    @GetMapping("/detail")
    public RestResult detail(@RequestParam(value = "id") String id) {
        return RestResult.success(permissionService.findById(id));
    }

    @PostMapping("/delete")
    public RestResult delete(@RequestBody Permission permission) {
        permissionService.deleteById(permission.getId());
        return RestResult.success();
    }

    @GetMapping("/queryByRole")
    public RestResult findByRoleId(String roleId) {
        return RestResult.success(
                permissionService.findPermissionsByRoles(Lists.list(roleId))
        );
    }

}