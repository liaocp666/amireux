package cn.liaocp.amireux.core.config;

import cn.liaocp.amireux.core.properties.AmireuxProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.time.temporal.Temporal;

/**
 * @author Chunping.Liao
 */
@Configuration
@EnableOpenApi
@RequiredArgsConstructor
public class SwaggerCoreApiConfig {

    private final AmireuxProperties amireuxProperties;

    @Bean
    public Docket coreDocket() {
        return new Docket(DocumentationType.OAS_30)
                .groupName("core")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.liaocp.amireux.core.controller"))
                .paths(PathSelectors.any())
                .build()
                .directModelSubstitute(Temporal.class, String.class);

    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Amireux Core API Documentation")
                .description("Documentation for Amireux Core API")
                .version(amireuxProperties.getVersion())
                .termsOfServiceUrl("https://github.com/LeacHar/amireux")
                .license("MIT License")
                .licenseUrl("https://github.com/LeacHar/amireux/blob/main/LICENSE")
                .build();
    }

}
