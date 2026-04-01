package com.jpa_spring.jpa_spring;

import org.h2.tools.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.SQLException;

@Configuration
public class H2ServerConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server h2WebServer() throws SQLException {
        // Isso cria um console do H2 rodando na porta 8082, totalmente imune aos erros 404 do Tomcat
        return Server.createWebServer("-web", "-webAllowOthers", "-webPort", "8082");
    }
}