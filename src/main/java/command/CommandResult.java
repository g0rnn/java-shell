package command;

public class CommandResult {

    private final boolean shouldExit;

    private CommandResult(boolean shouldExit) {
        this.shouldExit = shouldExit;
    }

    public static CommandResult continueShell() {
        return new CommandResult(false);
    }

    public static CommandResult exitShell() {
        return new CommandResult(true);
    }

    public boolean shouldExit() {
        return shouldExit;
    }
}
