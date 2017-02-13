package org.home.gat.command;

import com.google.common.base.Splitter;
import io.airlift.airline.Command;
import org.home.gat.CommandDispatcher;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Scanner;

@Command(name = ShellCommand.SHELL_COMMAND, description = "Interactive repl-shell")
public class ShellCommand extends GatCommand {

    static final String SHELL_COMMAND = "shell";

    private static final String DEFAULT_PROMPT = " > ";
    private static final String DEFAULT_QUIT_COMMAND_NAME = "quit";

    @Override
    public void run(ApplicationContext applicationContext) {
        CommandDispatcher commandDispatcher = applicationContext.getBean(CommandDispatcher.class);

        String greeting =
                String.format("Interactive repl-shell. Type '%s' or press Ctrl+C for exit", DEFAULT_QUIT_COMMAND_NAME);
        System.out.println(greeting);

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

            if (args.isEmpty() || SHELL_COMMAND.equalsIgnoreCase(args.get(0))) {
                continue;
            }
            if (DEFAULT_QUIT_COMMAND_NAME.equalsIgnoreCase(args.get(0))) {
                break;
            }
            commandDispatcher.dispatchCommand(args);
        }
    }
}
