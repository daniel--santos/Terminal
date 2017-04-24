package commands.annotations;

import commands.Command;

/**
 * Anotação para identificar um {@link Command} CLI.
 */

public @interface CLICommandExecutable {

    //Nome do comando
    //Ex: mover <argumentos>
    //name = "mover"
    String name();

    //Flags
//    String[] flags();

}
