package org.home.gat.command;

import io.airlift.airline.Command;
import org.home.gat.exception.QuitException;
import org.springframework.context.ApplicationContext;

@Command(name = "quit", description = "Quit")
public class QuitCommand extends GatCommand {

    @Override
    public void run(ApplicationContext applicationContext) {
        throw new QuitException();
    }
}
