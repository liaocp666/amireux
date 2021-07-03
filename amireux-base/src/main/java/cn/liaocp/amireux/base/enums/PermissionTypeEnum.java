package cn.liaocp.amireux.base.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Chunping.Liao
 * @date 2021/6/5
 */
@Getter
@AllArgsConstructor
public enum PermissionTypeEnum {

    MENU("menu", "菜单"),
    PAGE("page", "页面"),
    API("api", "接口");

    private String type;

    private String desc;

}
