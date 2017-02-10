package org.home.gat.command;

import io.airlift.airline.Arguments;
import io.airlift.airline.Command;
import io.airlift.airline.Help;
import io.airlift.airline.model.GlobalMetadata;
import org.springframework.context.ApplicationContext;

import javax.inject.Inject;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Command(name = "help", description = "Display help information")
public class HelpCommand implements GatCommand {

    @Inject
    public GlobalMetadata global;

    @Arguments
    public List<String> command = newArrayList();

    @Override
    public void run(ApplicationContext applicationContext) {
        Help.help(global, command);
    }
}
