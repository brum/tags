package org.home.gat.command;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;
import org.home.gat.tag.api.TagService;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Command(name = "add", description = "Add tag")
public class TagAddCommand extends GatCommand {

    @Arguments(title = "arguments", description = "name for new tag and name of parent tag", required = true)
    List<String> arguments;

    @Override
    public void run(ApplicationContext applicationContext) {
        String tagName = arguments.get(0);
        String parentTagName = arguments.size() > 1 ? arguments.get(1) : null;

        TagService tagService = applicationContext.getBean(TagService.class);
        tagService.add(tagName, parentTagName);
    }
}
