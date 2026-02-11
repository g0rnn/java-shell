public class Echo implements Command {

    @Override
    public void act(String[] param) {
        String output = String.join(" ", Arrays.copyOfRange(param, 1, param.length));
        System.out.println(output);
    }
}