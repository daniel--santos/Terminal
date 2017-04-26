package commands.annotations;

import commands.Command;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Anotação para identificar um {@link Command} CLI.
 */

@Target(TYPE)
@Retention(RUNTIME)
public @interface CLICommandExecutable {

    //Nome do comando
    //Ex: mover <argumentos>
    //name = "mover"
    String name();

    //Flags
    String flags() default "";

}
