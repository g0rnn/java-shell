package command;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.io.File;

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
			
			String pathEnv = System.getenv("PATH");
			String[] paths = pathEnv.split(System.getProperty("path.separator"));

			for (String path : paths) {
				File file = new File(path, builtinCmd); // 단지 이 경로를 가리키는 객체만 생성한 상태
				if (file.exists() && file.canExecute()) {
					System.out.println(builtinCmd + " is " + file.getAbsolutePath());
					return;
				}
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
