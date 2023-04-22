package UI.CLI.CLICommands;

import UI.Interfaces.Command;

import java.util.Map;

public class DisplayMenuCommand implements Command {

    private final Map<Integer, Command> commands;

    public DisplayMenuCommand(Map<Integer, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        System.out.println("Please choose an option: ");
        for (Integer key : commands.keySet()) {
            System.out.print(key + ". ");
            System.out.println( commands.get(key).getDescription());
        }
        System.out.println("");
    }

    @Override
    public String getDescription() {
        return "Display menu";
    }

}