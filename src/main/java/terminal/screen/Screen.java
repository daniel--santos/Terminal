package terminal.screen;

import terminal.shell.Command;

import java.awt.event.KeyEvent;
import java.util.List;

public interface Screen {
//	public void displayOutput(AsciiPanel screen);
	
	public Screen respondToUserInput(KeyEvent key);

	public void displayOutput();

	public List<String> getLastCommand();
}
