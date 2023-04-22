import Enums.SupportSqlDB;
import Enums.UIType;
import Library.Database.Adapters.BookManageSqlAdapter;
import Library.Database.Interfaces.BookManager;
import Library.Service.BookLibraryService;
import Library.Service.LibraryService;
import UI.UI;
import UI.UIFactory.UIFactory;

public class LMS {

    public static void main(String[] args) {
        BookManager dataHandler = new BookManageSqlAdapter(SupportSqlDB.SQLITE);

        LibraryService bookLibraryService = new BookLibraryService(dataHandler);

        UIFactory uiFactory = new UIFactory(bookLibraryService);

        UI ui = uiFactory.getUI(UIType.CLI);
        ui.show();
    }
}
