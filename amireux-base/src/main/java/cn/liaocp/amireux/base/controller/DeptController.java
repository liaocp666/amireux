package cn.liaocp.amireux.base.controller;

import cn.liaocp.amireux.core.AmireuxConstant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Chunping.Liao
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(DeptController.API)
public class DeptController {

    public final static String API = AmireuxConstant.API + "/dept";

}
