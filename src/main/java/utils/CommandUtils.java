package utils;

import commands.Command;
import commands.ExitCommand;

import java.util.HashSet;
import java.util.Set;

public class CommandUtils {

    private static Set<Class<? extends Command>> getCommandsClasses() {
        Set<Class<? extends Command>> commandsClasses = new HashSet<>();
        commandsClasses.add(ExitCommand.class);
        return commandsClasses;
    }

    private static <T extends Command> T carregarCommand(Class<? extends Command> commandClazz) {
//        T command =
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
