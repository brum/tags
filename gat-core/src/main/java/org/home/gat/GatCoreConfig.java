package org.home.gat;

import org.home.gat.tag.api.TagService;
import org.home.gat.tag.impl.TagStoreImpl;
import org.home.gat.tag.impl.TagServiceImpl;
import org.home.gat.tag.impl.TagStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(H2ServerConfiguration.class)
public class GatCoreConfig {

    @Bean
    TagStore tagStore() {
        return new TagStoreImpl();
    }

    @Bean
    TagService tagService(TagStore tagStore) {
        return new TagServiceImpl(tagStore);
    }


}
