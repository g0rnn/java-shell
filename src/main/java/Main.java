import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("$ ");
            String input = scanner.nextLine();
            String[] cmds = input.split(" ");

            if (cmds[0].equals("exit")) return;
            else if (cmds[0].equals("echo")) {
                String output = String.join(" ", Arrays.copyOfRange(cmds, 1, cmds.length));
                System.out.println(output);
            }
            else System.out.println(input + ": command not found");
        }
        
    }
}
