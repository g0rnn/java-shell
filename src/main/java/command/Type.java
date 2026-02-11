public class Type implements Command {

    @Override
    public void act(String[] param) {
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
    }
}