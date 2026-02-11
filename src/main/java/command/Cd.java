public class Cd implements Command {

    @Override
    public void act(String[] param) {
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
    }
}