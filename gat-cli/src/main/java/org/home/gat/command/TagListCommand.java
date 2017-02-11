package org.home.gat.command;

import com.google.common.base.Joiner;
import io.airlift.airline.Command;
import org.home.gat.tag.api.TagService;
import org.springframework.context.ApplicationContext;

@Command(name = "list", description = "List tags")
public class TagListCommand extends GatCommand {

    @Override
    public void run(ApplicationContext applicationContext) {
        TagService tagService = applicationContext.getBean(TagService.class);
        System.out.println(Joiner.on(System.lineSeparator()).join(tagService.list()));
    }
}
