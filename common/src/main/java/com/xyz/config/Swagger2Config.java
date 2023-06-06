package com.xyz.config;

import com.github.xiaoymin.knife4j.spring.extension.OpenApiExtensionResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger2配置
 *
 * @author xyz
 * 2023/5/30
 */
@Configuration
@EnableSwagger2WebMvc
public class Swagger2Config {

    /*引入Knife4j提供的扩展类*/
    private final OpenApiExtensionResolver openApiExtensionResolver;

    @Autowired
    public Swagger2Config(OpenApiExtensionResolver openApiExtensionResolver) {
        this.openApiExtensionResolver = openApiExtensionResolver;
    }

    private final static String groupName = "my";//组群名称-分组名称

    private final static String headerName = "token";//需要swagger每次调接口前携带的头信息的key
    //private final static String headerName2 = "test";//如果要多个请求头信息，自行解放注释

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())//文档信息
                .groupName(groupName)//组群名称
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.xyz"))//需要扫描的api所在目录
                .paths(PathSelectors.any())//匹配全部地址路径
                .build()
                .securitySchemes(securitySchemes())//配置安全方案
                .securityContexts(securityContexts())//配置安全方案所实现的上下文
                .extensions(openApiExtensionResolver.buildExtensions(groupName))//赋予插件体系
                ;
    }

    private List<SecurityScheme> securitySchemes() {
        List<SecurityScheme> apiKeyList = new ArrayList<>();
        //配置固定请求头
        ApiKey token_access = new ApiKey(headerName, headerName, "header");
        apiKeyList.add(token_access);
        return apiKeyList;
    }

    private List<SecurityContext> securityContexts() {
        List<SecurityContext> securityContextList = new ArrayList<>();
        List<SecurityReference> securityReferenceList = new ArrayList<>();
        //为每个api添加请求头
        securityReferenceList.add(new SecurityReference(headerName, scopes()));
        //以此类推
        //securityReferenceList.add(new SecurityReference(headerName2, scopes()));
        securityContextList.add(SecurityContext
                .builder()
                .securityReferences(securityReferenceList)
                .forPaths(PathSelectors.any())
                .build()
        );
        return securityContextList;
    }

    private AuthorizationScope[] scopes() {
        return new AuthorizationScope[]{new AuthorizationScope("global", "accessAnything")};//作用域为全局
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("myInitProject")
                .description("项目基础模板，集成Swagger、Security、MybatisPlus等")
                .termsOfServiceUrl("myServerUrI")
                .contact(new Contact("xyz", "myServerUrI", "designv@foxmail.com"))
                .version("1.0")
                .build();
    }

}