package com.gasstation.managementsystem.configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        List<Server> serverList = new ArrayList<>();
        serverList.add(new Server().url("http://localhost:8080"));
        serverList.add(new Server().url("https://hello-gas.herokuapp.com"));
        return new OpenAPI()
                // Thiết lập các server dùng để test api
                .servers(serverList)
                // info
                .info(new Info().title("Petrol Station Management System API")
                        .description("Sample OpenAPI 3.0")
                        .contact(new Contact()
                                .email("lehongquan15a@gmail.com")
                                .name("Petrol Station Management System")
                                .url("https://psms-2021.web.app/login"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://www.apache.org/licenses/LICENSE-2.0.html"))
                        .version("1.0.0"));
    }
}
