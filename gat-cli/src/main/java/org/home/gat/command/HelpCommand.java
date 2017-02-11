package org.home.gat.command;

import io.airlift.airline.Command;
import io.airlift.airline.Help;
import org.springframework.context.ApplicationContext;

@Command(name = "help", description = "Display help information")
public class HelpCommand extends GatCommand {

    @Override
    public void run(ApplicationContext applicationContext) {
        Help.help(global, command);
    }
}
