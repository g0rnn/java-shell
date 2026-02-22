package command;

import shell.ShellContext;

public interface Command {
    String name();

    CommandKind kind();

    CommandResult execute(CommandInput input, ShellContext context);
}
