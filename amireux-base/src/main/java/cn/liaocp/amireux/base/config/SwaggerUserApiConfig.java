package cn.liaocp.amireux.base.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.temporal.Temporal;

/**
 * @author Chunping.Liao
 * @date 2021/5/17
 */
@Configuration
@RequiredArgsConstructor
public class SwaggerUserApiConfig {

    private final ApiInfo apiInfo;

    @Bean
    public Docket userDocket() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("user")
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.liaocp.amireux.user.controller"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(Temporal.class, String.class);

    }

}
