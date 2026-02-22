package command;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import shell.ShellContext;

public class ExternalCommand implements Command {

    private final String name;
    private final Path executablePath;

    public ExternalCommand(String name, Path executablePath) {
        this.name = name;
        this.executablePath = executablePath;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public CommandKind kind() {
        return CommandKind.EXTERNAL;
    }

    @Override
    public CommandResult execute(CommandInput input, ShellContext context) {
        List<String> argv = new ArrayList<>();
        argv.add(executablePath.toString());
        argv.addAll(input.args());

        ProcessBuilder processBuilder = new ProcessBuilder(argv);
        processBuilder.directory(context.getCurrentDirectory().toFile());
        processBuilder.inheritIO();

        try {
            Process process = processBuilder.start();
            process.waitFor();
        } catch (IOException e) {
            System.out.println(name + ": execution failed");
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        return CommandResult.continueShell();
    }

    public Path executablePath() {
        return executablePath;
    }
}
