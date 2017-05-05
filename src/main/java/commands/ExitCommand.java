package commands;

import commands.annotations.CLICommandExecutable;

@CLICommandExecutable(name = "exit")
public class ExitCommand extends Command {

    public ExitCommand() {
        super();
    }

    public void execute() {
        System.exit(0);
    }

}
