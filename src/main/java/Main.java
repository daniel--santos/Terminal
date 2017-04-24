import terminal.TerminalApp;

import javax.swing.*;

//TODO:
//  1 - Isolar os recursos do AsciiPanel no pacote respectivo
//  2 - Criar Historico de comandos
public class Main {

    public static void main(String[] args) {
        TerminalApp app = new TerminalApp();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

}
