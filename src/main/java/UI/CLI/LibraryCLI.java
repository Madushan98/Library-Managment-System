package UI.CLI;

import Library.Service.LibraryService;
import UI.CLI.CLICommands.*;
import UI.Interfaces.Command;
import UI.Interfaces.UI;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LibraryCLI implements UI {

    private final LibraryService libraryService;
    private final Scanner scanner;
    private final Map<Integer, Command> commands;

    public LibraryCLI(LibraryService libraryService) {
        this.libraryService = libraryService;
        commands = new HashMap<>();
        this.scanner = new Scanner(System.in);
        addCommand();
    }

    @Override
    public void show() {
        start();
    }

    /**
     * adds all the available commands to the commands map
     */
    public void addCommand() {
        this.commands.put(1, new AddBookCommand(libraryService, scanner));
        this.commands.put(2, new RemoveBookCommand(libraryService, scanner));
        this.commands.put(3, new DisplayAllBooksCommand(libraryService));
        this.commands.put(4, new DisplayAvailableBooksCommand(libraryService));
        this.commands.put(5, new BorrowBookCommand(libraryService, scanner));
        this.commands.put(6, new ReturnBookCommand(libraryService, scanner));
        this.commands.put(7, new DisplayBorrowedBooksCommand(libraryService));
        this.commands.put(8, new DisplayOverdueBooksCommand(libraryService));
        this.commands.put(9, new SearchBookCommand(libraryService,scanner));
        this.commands.put(10, new DisplayMenuCommand(commands));
        this.commands.put(11, new ExitCommand());
    }

    /**
     * starts the CLI
     */
    public void start() {
        System.out.println("\n-------------Welcome to the Library Management System.-----------\n");

        Command command = commands.get(10);
        command.execute();
        while (true) {
            int choice = getChoice();
            command = commands.get(choice);
            if (command != null) {
                command.execute();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
            System.out.println("");

        }
    }

    /**
     * get user's input
     */
    private int getChoice() {
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("");
        return choice;
    }

}
