package terminal.shell;

import java.util.ArrayList;
import java.util.List;

public class Command {

    private String commandText;
    private List<String> flags;

    public Command() {
        flags = new ArrayList<>();
    }

    public String getCommandText() {
        return commandText;
    }

    public void setCommandText(String commandText) {
        this.commandText = commandText;
    }

    public List<String> getFlags() {
        return flags;
    }

    public void setFlags(List<String> flags) {
        this.flags = flags;
    }
}
