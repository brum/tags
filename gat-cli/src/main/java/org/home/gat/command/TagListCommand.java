package org.home.gat.command;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;
import org.home.gat.tag.api.Tag;
import org.home.gat.tag.api.TagService;
import org.springframework.context.ApplicationContext;

import java.util.List;

@Command(name = "list", description = "List tags")
public class TagListCommand extends GatCommand {

    @Arguments(title = "tag", description = "List tags under desired tag")
    String tagName;

    @Override
    public void run(ApplicationContext applicationContext) {
        TagService tagService = applicationContext.getBean(TagService.class);
        printTagList(tagService.list(tagName));
    }

    private void printTagList(List<Tag> tags) {
        for (Tag tag : tags) {
            String prefix = tag.isHasChildren() ? "+" : " ";
            System.out.println(prefix + tag.getName());
        }
    }
}
