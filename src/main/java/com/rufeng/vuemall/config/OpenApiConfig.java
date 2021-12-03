package com.rufeng.vuemall.config;

import com.fasterxml.jackson.databind.type.SimpleType;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.Operation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springdoc.core.customizers.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Type;

/*
 * Created with IntelliJ IDEA.
 * @Author: Pluto
 * @Date: 2021-09-15 22:09
 * @Package: com.pluto.mall.config
 * @Version: 1.0
 * @Description:
 */

/**
 * @author Pluto
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(title = "Api接口文档", description = "rufeng mall 项目",
                contact = @Contact(url = "https://www.baidu.com/", email = "chunfengh163@163.com", name = "rufeng"),
                license = @License(name = "Apache2.0", url = "https://www.apache.org/licenses/LICENSE-2.0.html")),
        externalDocs = @ExternalDocumentation(description = "项目部署文档", url = "/static/README.md"))
public class OpenApiConfig {
    private final Log logger = LogFactory.getLog(OpenApiConfig.class);

    @Bean
    public OpenApiCustomiser openApiCustomiser() {
        return openApi -> logger.debug("OpenApi customizer");
    }

    @Bean
    public OperationCustomizer operationCustomizer() {
        return new ActuatorOperationCustomizer() {
            @Override
            public Operation customize(Operation operation, HandlerMethod handlerMethod) {
                logger.debug("operation customizer");
                return super.customize(operation, handlerMethod);
            }
        };
    }

    @Bean
    public OpenApiBuilderCustomizer openApiBuilderCustomizer() {
        return openApiService -> logger.debug("OpenApiBuilder customizer");
    }

    @Bean
    public ParameterCustomizer parameterCustomizer() {
        return (parameterModel, methodParameter) -> {
            logger.debug("Parameter customizer");
            return parameterModel;
        };
    }


    @Bean
    public PropertyCustomizer propertyCustomizer() {
        return (property, type) -> {
            logger.debug("customizer property");
            Type t = type.getType();
            if (t instanceof SimpleType && ((SimpleType) t).getRawClass().equals(Void.class)) {
                return null;
            }
            return property;
        };
    }
}
