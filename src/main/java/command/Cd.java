package command;

import java.nio.file.Path;

import shell.ShellContext;

public class Cd extends BuiltinCommand {

    @Override
    public String name() {
        return "cd";
    }

    @Override
    public CommandResult execute(CommandInput input, ShellContext context) {
        if (input.args().isEmpty()) {
            System.out.println("cd: missing operand");
            return CommandResult.continueShell();
        }

        String target = input.args().get(0);
        Path targetPath = context.resolvePath(target);
        if (context.changeDirectory(targetPath)) {
            return CommandResult.continueShell();
        }

        System.out.println("cd: " + target + ": No such file or directory");
        return CommandResult.continueShell();
    }
}
