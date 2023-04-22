import java.util.Scanner;

import Enums.UIType;
import Library.Database.Abstract.DataManagerFactory;
import Library.Database.Enums.SupportSqlDB;
import Library.Database.Interfaces.BookManager;
import Library.Database.Interfaces.DataManager;
import Library.Database.Interfaces.RecordManager;
import Library.Service.BookLibraryService;
import Library.Service.LibraryService;
import UI.Interfaces.UI;
import UI.UIFactory.UIFactory;

public class LMS {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter 1 for CLI or 2 for GUI: ");
        int choice = scanner.nextInt();

        DataManager dataManager = new DataManagerFactory();
        BookManager bookManager = dataManager.GetBookManager(SupportSqlDB.SQLITE);
        RecordManager recordManager = dataManager.GetRecordManager(SupportSqlDB.SQLITE);

        LibraryService bookLibraryService = new BookLibraryService(bookManager, recordManager);

        UIFactory uiFactory = new UIFactory(bookLibraryService);

         UI ui;
        if (choice == 1) {
            ui = uiFactory.getUI(UIType.CLI);
        } else if (choice == 2) {
            ui = uiFactory.getUI(UIType.GUI);
        } else {
            System.out.println("Invalid choice!");
            return;
        }

        ui.show();
    }
}
