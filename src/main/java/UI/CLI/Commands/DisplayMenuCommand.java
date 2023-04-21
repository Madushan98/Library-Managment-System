package UI.CLI.Commands;

import UI.CLI.Interfaces.Command;

import java.util.Map;

public class DisplayMenuCommand implements Command {

    private final Map<Integer, Command> commands;

    public DisplayMenuCommand(Map<Integer, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void execute() {
        System.out.println("\n-------------Welcome to the Library Management System.-----------\n");
        System.out.println("Please choose an option: ");
        for (Integer key : commands.keySet()) {
            System.out.print(key + ". ");
            commands.get(key).getDescription();
        }
        System.out.println("");
    }

    @Override
    public void getDescription() {
        System.out.println("Display menu");
    }
}