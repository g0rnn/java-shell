package command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class Commands {

	private final Map<String, Consumer<String[]>> commands = new HashMap<>();
	
	public void init() {
		commands.put("exit", (param) -> System.exit(0));
		commands.put("echo", (param) -> {
			String output = String.join(" ", Arrays.copyOfRange(param, 1, param.length));
            System.out.println(output);
		});
		commands.put("type", (param) -> {
			String builtinCmd = param[1];
			
			if (commands.containsKey(builtinCmd)) {
				System.out.println(builtinCmd + " is a shell builtin");
				return;
			}
			System.out.println(builtinCmd + ": not found");
		});
	}
	
	public void act(String input) {
		String[] cmd = input.split(" ");
		Consumer<String[]> action = commands.getOrDefault(cmd[0], this::notFound);
		action.accept(cmd);
	}
	
	public void notFound(String[] param) {
		System.out.println(param[0] + ": command not found");
	}
}
