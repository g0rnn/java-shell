package command;

import shell.ShellContext;

public class Pwd extends BuiltinCommand {

    @Override
    public String name() {
        return "pwd";
    }

    @Override
    public CommandResult execute(CommandInput input, ShellContext context) {
        System.out.println(context.getCurrentDirectory());
        return CommandResult.continueShell();
    }
}
