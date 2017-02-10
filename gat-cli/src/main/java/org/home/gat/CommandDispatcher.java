package org.home.gat;

import io.airlift.airline.Cli;
import io.airlift.airline.Help;
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
        Cli.CliBuilder<Runnable> builder = Cli.<Runnable>builder("gat")
                .withDescription("The program for tagging files")
                .withDefaultCommand(Help.class)
                .withCommand(Help.class);

        Cli<Runnable> gatDispatcher = builder.build();

        gatDispatcher.parse(args).run();
    }
}
