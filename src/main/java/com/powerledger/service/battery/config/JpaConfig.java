package com.powerledger.service.battery.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = {"com.powerledger.service.*"})
// @EnableJpaAuditing
public class JpaConfig {
}
