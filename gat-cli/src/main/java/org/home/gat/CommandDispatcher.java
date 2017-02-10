package org.home.gat;

import io.airlift.airline.Cli;
import org.home.gat.command.GatCommand;
import org.home.gat.command.HelpCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class CommandDispatcher implements CommandLineRunner, ApplicationContextAware {

    private static final Logger log = LoggerFactory.getLogger(CommandDispatcher.class);

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void run(String... args) throws Exception {
        Cli.CliBuilder<GatCommand> builder = Cli.<GatCommand>builder("gat")
                .withDescription("The program for tagging files")
                .withDefaultCommand(HelpCommand.class)
                .withCommand(HelpCommand.class);

        Cli<GatCommand> gatDispatcher = builder.build();

        gatDispatcher.parse(args).run(applicationContext);
    }
}
