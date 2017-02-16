package org.home.gat;

import com.google.common.base.Splitter;
import io.airlift.airline.Cli;
import org.home.gat.command.GatCommand;
import org.home.gat.command.HelpCommand;
import org.home.gat.command.QuitCommand;
import org.home.gat.command.TagListCommand;
import org.home.gat.exception.QuitException;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

class MainLoop {

    private static final String DEFAULT_PROMPT = " > ";

    private final ApplicationContext applicationContext;
    private final Cli<GatCommand> gatDispatcher;

    MainLoop(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
        gatDispatcher = buildGatDispatcher();
    }

    private Cli<GatCommand> buildGatDispatcher() {
        Cli.CliBuilder<GatCommand> builder = Cli.<GatCommand>builder("gat")
                .withDescription("The program for tagging files")
                .withDefaultCommand(HelpCommand.class)
                .withCommands(Arrays.asList(
                        HelpCommand.class,
                        QuitCommand.class
                ));

        builder.withGroup("tags")
                .withDescription("Manage tags")
                .withDefaultCommand(TagListCommand.class)
                .withCommand(TagListCommand.class);

        return builder.build();
    }

    void run() {
        System.out.println("Interactive repl-shell.");
        //run default command - help
        gatDispatcher.parse().run(applicationContext);

        Scanner scanner = new Scanner(System.in);
        Splitter splitter = Splitter.on(" ")
                .omitEmptyStrings()
                .trimResults();
        while (true) {
            System.out.print(DEFAULT_PROMPT);

            if (!scanner.hasNextLine()) {
                break;
            }

            List<String> args = splitter.splitToList(scanner.nextLine());

            if (args.isEmpty()) {
                continue;
            }

            try {
                gatDispatcher.parse(args).run(applicationContext);
            } catch (QuitException ex) {
                System.out.println(ex.getMessage());
                break;
            } catch (Exception ex) {
                System.err.println(ex.getMessage());
            }
        }
    }
}
