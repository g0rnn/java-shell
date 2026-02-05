import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            String[] cmds = input.split(" ");

            if (cmds[0].equals("exit")) return;
            else if (cmds[0].equals("echo")) {
                System.out.println(cmds[1]);
            }
            else System.out.println(command + ": command not found");
        }
        
    }
}
