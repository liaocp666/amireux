package cn.liaocp.amireux.base.controller;

import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

import cn.liaocp.amireux.base.domain.Dept;
import cn.liaocp.amireux.base.service.DeptService;
import cn.liaocp.amireux.core.AmireuxConstant;
import cn.liaocp.amireux.core.domain.RestResult;
import lombok.RequiredArgsConstructor;

import javax.validation.Valid;
import java.util.Objects;

/**
 * @author Chunping.Liao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(DeptController.API)
public class DeptController {

	public final static String API = AmireuxConstant.API + "/dept";

	private final DeptService deptService;

	@PostMapping("/save")
	public RestResult save(@Valid @RequestBody Dept dept) {
		return RestResult.success(deptService.save(dept));
	}

	@GetMapping("/detail")
	public RestResult detail(@RequestParam(value = "id") String id) {
		return RestResult.success(deptService.findAllById(id));
	}

	@PostMapping("/delete")
	public RestResult delete(@RequestBody Dept dept) {
		deptService.deleteById(Objects.requireNonNull(dept).getId());
		return RestResult.success();
	}

	@GetMapping("/page")
	public RestResult page(Dept dept) {
		return RestResult.success(deptService.page(dept));
	}

}
