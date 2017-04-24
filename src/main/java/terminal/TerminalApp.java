package terminal;

import asciiPanel.AsciiPanel;
import terminal.constant.Commands;
import terminal.constant.Constants;
import terminal.screen.Screen;
import terminal.screen.TerminalScreen;
import terminal.shell.Command;
import utils.KeyboardUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class TerminalApp extends JFrame implements KeyListener {

	private AsciiPanel terminal;
	private Screen screen;

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

		add(terminal);
		pack();
		addKeyListener(this);
		repaint();
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
			remove(terminal);
			terminal = new AsciiPanel(Constants.WINDOWS_WIDTH, Constants.WINDOWS_HEIGHT);
			screen = new TerminalScreen(terminal);
			add(terminal);
			pack();
		} else {
			//Acrescenta as letras ao terminal
			screen = screen.respondToUserInput(keyEvent);
		}
		repaint();
	}

	public boolean run() {
		Command comando = screen.getLastCommand();

		if(comando != null) {
			executarComando(comando);
		}

//		String commands = text.toString().replace(">", "");
//	    saida = shell.executeCommand(commands);
		return false;
	}

	private void executarComando(Command comando) {
		if(comando.getCommandText().equals(Commands.EXIT.getCommand())) {
//			this.setVisible(false);
			System.exit(0);
		}
	}


	//
	@Override
	public void keyReleased(KeyEvent e) { }

	@Override
	public void keyTyped(KeyEvent e) { }
}
