package com.example.keyvalue.config;

import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class KeyCloakConfig {

    @Bean
    public KeycloakSpringBootConfigResolver keycloakConfigResolverOne() {
        return new KeycloakSpringBootConfigResolver();
    }
}
