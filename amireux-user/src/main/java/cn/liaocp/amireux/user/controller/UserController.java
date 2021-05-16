package cn.liaocp.amireux.user.controller;

import cn.liaocp.amireux.core.AmireuxConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@RestController
@RequestMapping(UserController.API)
public class UserController {

    public final static String API = AmireuxConstant.API + "/user";

}
