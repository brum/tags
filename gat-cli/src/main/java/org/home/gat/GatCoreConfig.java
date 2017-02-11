package org.home.gat;

import org.home.gat.tag.api.TagService;
import org.home.gat.tag.impl.TagServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatCoreConfig {

    @Bean
    TagService tagService() {
        return new TagServiceImpl();
    }


}
