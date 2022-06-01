package com.sven7.ntserver.configure;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "ntserver.echo.server")
public class EchoServerConfigureProperties {
    private int port;
}
