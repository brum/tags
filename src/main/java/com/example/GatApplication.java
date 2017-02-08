package com.example;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(exclude = {JmxAutoConfiguration.class})
public class GatApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GatApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .web(false)
                .run(args);
    }
}
