package cn.liaocp.amireux.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Chunping.Liao
 */
@Data
@Component
@ConfigurationProperties(prefix = "amireux")
public class AmireuxProperties {

    /**
     * Name of the project
     */
    private String name = "Amireux";

    /**
     * Version of the project
     */
    private String version = "1.0.0";

}
