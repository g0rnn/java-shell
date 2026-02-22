package command;

import shell.ShellContext;

public class Echo extends BuiltinCommand {

    @Override
    public String name() {
        return "echo";
    }

    @Override
    public CommandResult execute(CommandInput input, ShellContext context) {
        String output = String.join(" ", input.args());
        System.out.println(output);
        return CommandResult.continueShell();
    }
}
