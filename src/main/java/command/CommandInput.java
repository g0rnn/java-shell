package command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CommandInput {

    private final String name;
    private final List<String> args;

    public CommandInput(String name, List<String> args) {
        this.name = name;
        this.args = new ArrayList<>(args);
    }

    public String name() {
        return name;
    }

    public List<String> args() {
        return Collections.unmodifiableList(args);
    }
}
