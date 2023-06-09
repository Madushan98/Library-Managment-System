package UI.CLI.CLICommands;

import UI.Interfaces.Command;

public class ExitCommand implements Command {

    @Override
    public void execute() {
        System.out.println("Exiting the library system. Goodbye!");
        System.exit(0);
    }

    @Override
    public String getDescription() {
        return "Exit the library system";
    }
}
