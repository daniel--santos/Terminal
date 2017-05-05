package commands;

import asciiPanel.AsciiPanel;
import commands.annotations.CLICommandExecutable;
import terminal.constant.Constants;
import terminal.screen.Screen;
import terminal.screen.TerminalScreen;

@CLICommandExecutable(name = "resize", flags = "h, w")
public class ResizeCommand extends Command {

    public ResizeCommand() {
        super();
    }

    public void execute() {
        AsciiPanel terminal = null;
        if(getWidth() == 0 && getHeight() == 0) {
            terminal = new AsciiPanel();
        } else {
            terminal = new AsciiPanel(getWidth(), getHeight());
        }
        Screen screen = new TerminalScreen(terminal);
        getTerminalApp().remove(getTerminalApp().getTerminal());
        getTerminalApp().setTerminal(terminal);
        getTerminalApp().add(terminal);
        getTerminalApp().setScreen(screen);
        getTerminalApp().pack();
    }

    private int getHeight() {
        if(getFlags().get("h") != null) {
            return Integer.parseInt((String) getFlags().get("h"));
        }
        return 0;
    }

    private int getWidth() {
        if(getFlags().get("w") != null) {
            return Integer.parseInt((String) getFlags().get("w"));
        }
        return 0;
    }

}
