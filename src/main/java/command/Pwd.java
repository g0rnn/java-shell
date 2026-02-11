public class Pwd implements Command {

    @Override
    public void act(String[] param) {
        String currentDir = System.getProperty("user.dir");
			System.out.println(currentDir);
    }
}