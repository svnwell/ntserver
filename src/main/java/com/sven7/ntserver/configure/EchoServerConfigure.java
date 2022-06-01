package com.sven7.ntserver.configure;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Configuration
@EnableConfigurationProperties(EchoServerConfigureProperties.class)
public class EchoServerConfigure {
    private final EchoServerConfigureProperties properties;

    public EchoServerConfigure(EchoServerConfigureProperties properties) {
        this.properties = properties;
    }
}
