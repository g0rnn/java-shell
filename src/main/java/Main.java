import java.util.Scanner;

import command.Commands;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        Commands cmds = new Commands();
        cmds.init();
        
        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            cmds.act(input);
        }
        
    }
    
}
