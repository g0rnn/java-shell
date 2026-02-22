package command;

import java.nio.file.Path;
import java.util.Optional;

import shell.ShellContext;

public class Type extends BuiltinCommand {

    private final Commands commands;

    public Type(Commands commands) {
        this.commands = commands;
    }

    @Override
    public String name() {
        return "type";
    }

    @Override
    public CommandResult execute(CommandInput input, ShellContext context) {
        if (input.args().isEmpty()) {
            return CommandResult.continueShell();
        }

        String commandName = input.args().get(0);
        if (commands.isBuiltin(commandName)) {
            System.out.println(commandName + " is a shell builtin");
            return CommandResult.continueShell();
        }

        Optional<Path> executablePath = commands.findExternalExecutable(commandName, context);
        if (executablePath.isPresent()) {
            System.out.println(commandName + " is " + executablePath.get());
            return CommandResult.continueShell();
        }

        System.out.println(commandName + ": not found");
        return CommandResult.continueShell();
    }
}
