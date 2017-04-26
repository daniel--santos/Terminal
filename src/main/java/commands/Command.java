package commands;

import asciiPanel.AsciiPanel;
import terminal.TerminalApp;
import terminal.screen.Screen;

import java.io.Serializable;
import java.util.Map;

public abstract class Command implements Serializable {

    private TerminalApp terminalApp;

    private String name;
    private Map<String, Object> flags;

    public abstract void execute();

    public TerminalApp getTerminalApp() {
        return terminalApp;
    }

    public void setTerminalApp(TerminalApp terminalApp) {
        this.terminalApp = terminalApp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Object> getFlags() {
        return flags;
    }

    public void setFlags(Map<String, Object> flags) {
        this.flags = flags;
    }
}
