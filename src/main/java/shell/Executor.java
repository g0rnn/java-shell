package shell;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import command.Command;
import command.CommandInput;
import command.CommandResult;
import command.Commands;

public class Executor {

    private final Commands commands;
    private final ShellContext context;

    public Executor(Commands commands, ShellContext context) {
        this.commands = commands;
        this.context = context;
    }

    public CommandResult runCommand(String rawInput) {
        Optional<CommandInput> maybeInput = parseInput(rawInput);
        if (maybeInput.isEmpty()) {
            return CommandResult.continueShell();
        }

        CommandInput input = maybeInput.get();
        Optional<Command> command = commands.find(input.name(), context);
        if (command.isEmpty()) {
            System.out.println(input.name() + ": command not found");
            return CommandResult.continueShell();
        }

        return command.get().execute(input, context);
    }

    private Optional<CommandInput> parseInput(String rawInput) {
        if (rawInput == null) {
            return Optional.empty();
        }

        String trimmedInput = rawInput.trim();
        if (trimmedInput.isEmpty()) {
            return Optional.empty();
        }

        String[] tokens = trimmedInput.split("\\s+");
        String commandName = tokens[0];
        List<String> args = new ArrayList<>();
        for (int i = 1; i < tokens.length; i++) {
            args.add(tokens[i]);
        }

        return Optional.of(new CommandInput(commandName, args));
    }
}
