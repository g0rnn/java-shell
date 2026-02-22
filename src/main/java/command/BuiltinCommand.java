package command;

public abstract class BuiltinCommand implements Command {
    @Override
    public CommandKind kind() {
        return CommandKind.BUILTIN;
    }
}
