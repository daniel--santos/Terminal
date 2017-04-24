package terminal.constant;

import java.util.Optional;
import java.util.stream.Stream;

public enum Commands {

    EXIT("EXIT");

    private String command;

    private Commands(String command) {
        this.command = command;
    }

    public static Commands valueOfEnum(String commandText) {
        Optional<Commands> command = Stream.of(values()).filter(c -> c.getCommand().equals(commandText)).findFirst();

        if(command.isPresent()) {
            return command.get();
        }

        return null;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }
}
