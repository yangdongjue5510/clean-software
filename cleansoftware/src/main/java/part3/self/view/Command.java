package part3.self.view;

import java.util.Arrays;
import part3.self.view.exception.CommandNotFoundException;

public enum Command {
    ADD("AddEmp");

    private final String commandValue;

    Command(final String commandValue) {
        this.commandValue = commandValue;
    }

    public static Command of(final String input) {
        return Arrays.stream(values())
                .filter(command -> command.commandValue.equals(input))
                .findFirst()
                .orElseThrow(CommandNotFoundException::new);
    }
}
