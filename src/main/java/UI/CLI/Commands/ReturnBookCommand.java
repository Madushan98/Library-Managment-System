package UI.CLI.Commands;

import Library.Service.LibraryService;
import UI.CLI.Interfaces.Command;

import java.util.Scanner;

public class ReturnBookCommand implements Command {

        private final LibraryService libraryService;
        private final Scanner scanner;

        public ReturnBookCommand(LibraryService libraryService, Scanner scanner) {
            this.libraryService = libraryService;
            this.scanner = scanner;
        }

        @Override
        public void execute() {
                // TODO: Implement this method
        }

    @Override
    public void getDescription() {
        System.out.print("Return a book");
        System.out.println("");
    }
}
