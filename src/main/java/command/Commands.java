package command;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import shell.ShellContext;

public class Commands {

    private final Map<String, BuiltinCommand> builtinCommands = new HashMap<>();

    public Commands() {
        register(new Exit());
        register(new Echo());
        register(new Pwd());
        register(new Cd());
        register(new Type(this));
    }

    private void register(BuiltinCommand command) {
        builtinCommands.put(command.name(), command);
    }

    public Optional<Command> find(String commandName, ShellContext context) {
        BuiltinCommand builtin = builtinCommands.get(commandName);
        if (builtin != null) {
            return Optional.of(builtin);
        }

        Optional<Path> externalExecutable = findExternalExecutable(commandName, context);
        return externalExecutable.map(path -> new ExternalCommand(commandName, path));
    }

    public boolean isBuiltin(String commandName) {
        return builtinCommands.containsKey(commandName);
    }

    public Optional<Path> findExternalExecutable(String commandName, ShellContext context) {
        if (commandName == null || commandName.isBlank()) {
            return Optional.empty();
        }

        if (commandName.contains("/")) {
            Path path = context.resolvePath(commandName);
            if (isExecutable(path)) {
                return Optional.of(path.toAbsolutePath().normalize());
            }
            return Optional.empty();
        }

        String pathEnv = System.getenv("PATH");
        if (pathEnv == null || pathEnv.isBlank()) {
            return Optional.empty();
        }

        String[] paths = pathEnv.split(System.getProperty("path.separator"));
        for (String pathEntry : paths) {
            Path candidate = Paths.get(pathEntry).resolve(commandName);
            if (isExecutable(candidate)) {
                return Optional.of(candidate.toAbsolutePath().normalize());
            }
        }

        return Optional.empty();
    }

    private boolean isExecutable(Path path) {
        return Files.exists(path) && Files.isRegularFile(path) && Files.isExecutable(path);
    }
}
