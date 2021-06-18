package cn.liaocp.amireux.user.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Chunping.Liao
 * @date 2021/5/30
 * @see cn.liaocp.amireux.user.domain.Permission
 */
@Data
public class DynamicMenu implements Serializable {

    private String id;

    private String title;

    private String icon;

    private String url;

    private String template;

    private List<DynamicMenu> children;

}
