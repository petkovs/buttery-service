package com.powerledger.service.battery.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(title = "Battery Service", version = "v1.0", description = "Battery API Service"))
public class SpringDocConfig {}
