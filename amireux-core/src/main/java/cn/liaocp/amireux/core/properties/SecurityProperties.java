package cn.liaocp.amireux.core.properties;

import lombok.Data;

import java.util.Set;

/**
 * Load application.yml fle configuration
 *
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Data
public class SecurityProperties {

    /**
     * Token expiry time (hour)
     * 168 hours by default
     */
    private Integer tokenExpireTime = 168;

    /**
     * Token expiry time (hour)
     * 8760 hours (One year) by default
     */
    private Integer secretExpireTime = 8760;

    /**
     * Anonymous Access URL
     */
    private Set<String> whiteList;

}
