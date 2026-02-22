import java.util.Scanner;

import command.CommandResult;
import command.Commands;
import shell.Executor;
import shell.ShellContext;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Commands commands = new Commands();
        ShellContext context = new ShellContext();
        Executor executor = new Executor(commands, context);

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            CommandResult result = executor.runCommand(input);
            if (result.shouldExit()) {
                break;
            }
        }

        scanner.close();
    }
}
