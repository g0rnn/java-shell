package command;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.io.File;
import java.io.IOException;

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
		commands.put("pwd", (param) -> {
			String currentDir = System.getProperty("user.dir");
			System.out.println(currentDir);
		});
		commands.put("cd", (param) -> {
			if (param.length < 2) {
				System.out.println("cd: missing operand");
				return;
			}
			String targetDir = param[1];
			File dir = new File(targetDir);
			if (dir.exists() && dir.isDirectory()) {
				System.setProperty("user.dir", dir.getAbsolutePath());
			} else {
				System.out.println("cd: " + targetDir + ": No such file or directory");
			}
		});
	}
	
	public void act(String input) {
		String[] cmd = input.split(" ");
		Consumer<String[]> action = commands.getOrDefault(cmd[0], this::notCommand);
		action.accept(cmd);
	}
	
	public void notCommand(String[] param) {
		String cmd = param[0];
		String pathEnv = System.getenv("PATH");
		String[] paths = pathEnv.split(System.getProperty("path.separator"));

		for (String path : paths) {
			File exe = new File(path, cmd); // 단지 이 경로를 가리키는 객체만 생성한 상태
			if (exe.exists() && exe.canExecute()) {
				List<String> argv = new ArrayList<>();
				argv.add(cmd);
				for (int i = 1; i < param.length; i++) argv.add(param[i]);

				ProcessBuilder pb = new ProcessBuilder(argv);
				pb.inheritIO();

				try {
					Process p = pb.start();
					p.waitFor();
				} catch (IOException e) {
					// 실행 자체가 실패한 경우
					System.out.println(cmd + ": execution failed");
				} catch (InterruptedException e) {
					// 인터럽트 상태 복구(관례)
					Thread.currentThread().interrupt();
				}
				return;
			}
		}

		System.out.println(cmd + ": command not found");
	}
}
