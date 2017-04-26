package commands;

import asciiPanel.AsciiPanel;
import commands.annotations.CLICommandExecutable;
import terminal.constant.Constants;
import terminal.screen.Screen;
import terminal.screen.TerminalScreen;

@CLICommandExecutable(name = "resize", flags = "x, y")
public class ResizeCommand extends Command {

    public ResizeCommand() {

    }

    public void execute() {
        AsciiPanel terminal = new AsciiPanel(Constants.WINDOWS_WIDTH, Constants.WINDOWS_HEIGHT);
        Screen screen = new TerminalScreen(terminal);
        getTerminalApp().remove(getTerminalApp().getTerminal());
        getTerminalApp().setTerminal(terminal);
        getTerminalApp().add(terminal);
        getTerminalApp().setScreen(screen);
        getTerminalApp().pack();
    }

}
