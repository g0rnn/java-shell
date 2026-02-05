import java.util.Scanner;
import java.util.StringBuilder;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            String[] cmds = input.split(" ");

            if (cmds[0].equals("exit")) return;
            else if (cmds[0].equals("echo")) {
                StringBuilder sb = new StringBuilder();
                for (int i = 1; i < cmds.length; i++) sb.append(cmds[i]).append(" ");
                System.out.println(sb);
            }
            else System.out.println(command + ": command not found");
        }
        
    }
}
