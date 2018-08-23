package com.mmc.springbootdemo.config;

import io.undertow.UndertowOptions;
import org.springframework.boot.web.embedded.undertow.UndertowServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnderTowerConfig {

    //替换tomcat的undertower配置
    @Bean
    UndertowServletWebServerFactory embeddedServletContainerFactory() {

        UndertowServletWebServerFactory factory = new UndertowServletWebServerFactory();

        factory.addBuilderCustomizers(builder -> builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true));

        return factory;
    }
}
