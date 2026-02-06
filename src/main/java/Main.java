import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Consumer;

import command.Commands;

public class Main {

    private static final Map<String, Consumer<String[]>> commads = new HashMap<>();

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Commands cmds = new Commands();
        
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            cmds.act(input);
        }
        
    }
    
}
