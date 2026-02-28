package com.revshop.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI revShopOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("RevShop E-Commerce API")
                        .description("Comprehensive REST API documentation for RevShop E-Commerce Platform. This API provides endpoints for user authentication, product management, order processing, and cart operations.")
                        .version("2.0.0")
                        .contact(new Contact()
                                .name("RevShop Development Team")
                                .email("support@revshop.com")
                                .url("https://www.revshop.com"))
                        .license(new License()
                                .name("MIT License")
                                .url("https://opensource.org/licenses/MIT")))
                .servers(List.of(
                        new Server()
                                .url("http://localhost:9090/api")
                                .description("Development Server"),
                        new Server()
                                .url("https://api.revshop.com/api")
                                .description("Production Server")
                ));
    }
}
