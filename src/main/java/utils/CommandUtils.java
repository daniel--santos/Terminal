package utils;

import commands.Command;
import commands.ExitCommand;
import commands.ResizeCommand;
import commands.annotations.CLICommandExecutable;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

public class CommandUtils {

    private static Set<Class<? extends Command>> getCommandsClasses() {
        Set<Class<? extends Command>> commandsClasses = new HashSet<>();
        commandsClasses.add(ExitCommand.class);
        commandsClasses.add(ResizeCommand.class);
        return commandsClasses;
    }

    private static <T extends Command> T carregarCommand(Class<? extends Command> commandClazz) {
        T command = EntidadeUtil.instanciarObjeto((Class<T>) commandClazz);

        command.setName(getAnnotation(commandClazz, CLICommandExecutable.class).name());

        return command;
    }

    public static <T extends Command, A extends Annotation> A getAnnotation(Class<T> clazz, Class<A> clazzAnnotation) {
        Optional<Annotation> optionalAnnotation = Stream.of(clazz.getDeclaredAnnotations()).filter(ant -> clazzAnnotation.isInstance(ant)).findFirst();

        if(optionalAnnotation.isPresent()) {
            return (A) optionalAnnotation.get();
        }

        return null;
    }

    public static Set<? extends Command> loadCommands() {
        Set<Class<? extends Command>> commandsClasses = getCommandsClasses();
        Set<? extends Command> commands = new HashSet<>();

        for(Class<? extends Command> c : commandsClasses) {
            commands.add(carregarCommand(c));
        }

        return commands;
    }



}
