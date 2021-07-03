package cn.liaocp.amireux.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cn.liaocp.amireux.core.AmireuxConstant;
import lombok.RequiredArgsConstructor;

/**
 * @author Chunping.Liao
 * @date 2021/6/20
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(UserController.API)
public class UserController {

	public final static String API = AmireuxConstant.API + "/user";
	
}
