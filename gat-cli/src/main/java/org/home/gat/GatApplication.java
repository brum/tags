package org.home.gat;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {JmxAutoConfiguration.class})
@Import(GatCoreConfig.class)
public class GatApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(GatApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .web(false)
                .run(args);
    }

    @Bean
    CommandDispatcher commandDispatcher() {
        return new CommandDispatcher();
    }
}
