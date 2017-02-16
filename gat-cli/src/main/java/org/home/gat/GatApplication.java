package org.home.gat;

import org.springframework.boot.Banner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jmx.JmxAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

@SpringBootApplication(exclude = {JmxAutoConfiguration.class})
@Import(GatCoreConfig.class)
public class GatApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = new SpringApplicationBuilder(GatApplication.class)
                .bannerMode(Banner.Mode.OFF)
                .logStartupInfo(false)
                .web(false)
                .run(args);

        MainLoop mainLoop = applicationContext.getBean(MainLoop.class);
        mainLoop.run();
    }

    @Bean
    MainLoop mainLoop(ApplicationContext applicationContext) {
        return new MainLoop(applicationContext);
    }
}
