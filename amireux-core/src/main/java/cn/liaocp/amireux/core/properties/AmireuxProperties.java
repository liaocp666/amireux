package cn.liaocp.amireux.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author Chunping.Liao
 * @date 2021/5/16
 */
@Data
@ConfigurationProperties(prefix = "amireux")
public class AmireuxProperties {

    /**
     * project name
     */
    private String name = "amireux";

    private String version = "1.0.0";

    private SecurityProperties security;

}
