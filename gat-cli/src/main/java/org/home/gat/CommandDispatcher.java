package org.home.gat;

import io.airlift.airline.Cli;
import io.airlift.airline.ParseException;
import org.home.gat.command.GatCommand;
import org.home.gat.command.HelpCommand;
import org.home.gat.command.ShellCommand;
import org.home.gat.command.TagListCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Arrays;
import java.util.List;

public class CommandDispatcher implements CommandLineRunner, ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(CommandDispatcher.class);

    private final Cli<GatCommand> gatDispatcher;

    private ApplicationContext applicationContext;

    CommandDispatcher() {

        Cli.CliBuilder<GatCommand> builder = Cli.<GatCommand>builder("gat")
                .withDescription("The program for tagging files")
                .withDefaultCommand(ShellCommand.class)
                .withCommands(Arrays.asList(
                        HelpCommand.class,
                        ShellCommand.class
                ));

        builder.withGroup("tags")
                .withDescription("Manage tags")
                .withDefaultCommand(TagListCommand.class)
                .withCommand(TagListCommand.class);

        gatDispatcher = builder.build();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void dispatchCommand(List<String> args) {
        try {
            gatDispatcher.parse(args).run(applicationContext);
        } catch (ParseException ex) {
            System.err.println(ex.getMessage());
        }
    }

    @Override
    public void run(String... args) throws Exception {
        dispatchCommand(Arrays.asList(args));
    }
}
