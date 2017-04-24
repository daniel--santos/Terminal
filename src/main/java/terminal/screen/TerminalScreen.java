package terminal.screen;

import asciiPanel.AsciiColor;
import asciiPanel.AsciiPanel;
import terminal.constant.Commands;
import terminal.constant.Constants;
import terminal.shell.Command;
import utils.KeyboardUtils;

import java.awt.event.KeyEvent;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalScreen implements Screen {
	
	private String terminalName;
	
//	List<String> lastCommands = new Linked
	private AsciiPanel terminal;

	private int height;
	private int width;
	
	private StringBuilder textLine;

	public TerminalScreen(AsciiPanel terminal) {
		this.terminal = terminal;
		this.height = terminal.getHeightInCharacters();
		this.width = terminal.getWidthInCharacters();
		this.textLine = new StringBuilder(Constants.INICIALIZADOR_TERMINAL);
	}

	public void displayOutput() {
		terminal.write(getTerminalName(), 0, 0, AsciiColor.BRIGHT_CYAN, AsciiColor.MAGENTA);
		terminal.write(textLine.toString(), 1, height - 1);
	}

	public Screen respondToUserInput(KeyEvent key) {
		if(key.isActionKey()) {
			return this;
		}

		//Apagar
		if(KeyboardUtils.isBackSpace(key.getKeyCode())) {
			if(textLine.length() > 1) {
				textLine.deleteCharAt(textLine.length() - 1);
			}			
		} 
		
		//Adicionar um caracter
		else {
			textLine.append(key.getKeyChar());
		}
		
		return this;
	}

	public Command getLastCommand() {
		String linhaComando = textLine.toString();

		if(linhaComando.length() > 1) {
			List<String> tokens = Arrays.asList(linhaComando.substring(1, linhaComando.length()).split(" "));

			Commands commands = Commands.valueOfEnum(tokens.get(0));

			if(commands != null) {
				Command command = new Command();
				command.setCommandText(tokens.get(0));

				if(tokens.size() > 1) {
					command.setFlags(tokens.subList(1, tokens.size() - 1));
				}

				return command;
			}
		}

		return null;
	}

	//Utils
	private String getTerminalName() {
		if(terminalName == null) {
			String espacosEmBranco = Stream.generate(() -> String.valueOf(" ")).limit(width - Constants.TERMINAL_NAME.length()).collect(Collectors.joining());
			terminalName = Constants.TERMINAL_NAME + espacosEmBranco;
		}
		return terminalName;
	}

	//Gets e sets
	public AsciiPanel getTerminal() {
		return terminal;
	}

	public void setTerminal(AsciiPanel terminal) {
		this.terminal = terminal;
	}
}
