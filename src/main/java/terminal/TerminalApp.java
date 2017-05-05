package terminal;

import asciiPanel.AsciiPanel;
import commands.Command;
import terminal.screen.Screen;
import terminal.screen.TerminalScreen;
import utils.CommandUtils;
import utils.KeyboardUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.util.List;

public class TerminalApp extends JFrame implements KeyListener {

	public static AsciiPanel terminal;
	public static Screen screen;
	private Set<? extends Command> commands;

	public TerminalApp() {
		super();
		terminal = new AsciiPanel();
		screen = new TerminalScreen(terminal);

		//Set NOT Resizable
		this.setResizable(false);

		//Remover a borda da janela
		this.setUndecorated(true);

		//Tornar a tela arrastavel com o mouse
		adicionarDraggableMouse();

//		screen = new AsciiPanel(Constants.WINDOWS_WIDTH, Constants.WINDOWS_HEIGHT);

		//Da set da posição no meio da tela
		setLocationRelativeTo(null);

		//Adiciona Terminal(JPanel)
		add(terminal);

		//Dimensiona o panel
		pack();

		//Adiciona o um listner para as teclas
		addKeyListener(this);

		//Reconstroi a tela
		repaint();

		//Carrega os comandos
		commands = CommandUtils.loadCommands();
	}

	//TODO: Verificar sintaxe Java 8 para listerners
	private void adicionarDraggableMouse() {
		final Point offset = new Point();
		addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(final MouseEvent e) {
				offset.setLocation(e.getPoint());
			}
		});
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(final MouseEvent e) {
				setLocation(e.getXOnScreen() - offset.x, e.getYOnScreen() - offset.y);
			}
		});
	}

	@Override
	public void repaint() {
		terminal.clear();
		screen.displayOutput();
		super.repaint();
	}

	@Override
	public void keyPressed(KeyEvent keyEvent) {
		if(KeyboardUtils.isKeyEnter(keyEvent.getKeyCode())) {
			run();
		} else {
			//TODO: Tratar teclas especiais

			//Acrescenta as letras ao terminal
			screen = screen.respondToUserInput(keyEvent);
		}
		repaint();
	}

	public boolean run() {
		List<String> tokenLastCommand = screen.getLastCommand();
		Command command = commands.stream().filter(c -> c.getName().toUpperCase().equals(tokenLastCommand.get(0).toUpperCase())).findFirst().get();
		if(tokenLastCommand.size() > 1) {
			for(String flag : tokenLastCommand.subList(1, tokenLastCommand.size())) {
				String[] valores = flag.split("=");
				command.getFlags().put(valores[0], valores[1]);
			}
		} else {
			command.getFlags().clear();
		}

		command.setTerminalApp(this);
		command.execute();

		return false;
	}

	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }

	public AsciiPanel getTerminal() {
		return terminal;
	}

	public void setTerminal(AsciiPanel terminal) {
		this.terminal = terminal;
	}

	public Screen getScreen() {
		return screen;
	}

	public void setScreen(Screen screen) {
		this.screen = screen;
	}
}
