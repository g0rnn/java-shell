package command;

import shell.ShellContext;

public class Exit extends BuiltinCommand {

    @Override
    public String name() {
        return "exit";
    }

    @Override
    public CommandResult execute(CommandInput input, ShellContext context) {
        return CommandResult.exitShell();
    }
}
