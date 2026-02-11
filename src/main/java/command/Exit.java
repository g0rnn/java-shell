public class Exit implements Command {

    @Override
    public void act(String[] param) {
        System.exit(0);
    }
}