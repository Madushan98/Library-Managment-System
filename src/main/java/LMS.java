import Enums.SupportSqlDB;
import Enums.UIType;
import Library.Database.Adapters.Sql;
import Library.Database.Interfaces.DataHandler;
import Library.Service.BookLibraryService;
import Library.Service.LibraryService;
import UI.UI;
import UI.UIFactory.UIFactory;

public class LMS {

    public static void main(String[] args) {
        DataHandler dataHandler = new Sql(SupportSqlDB.SQLITE);

        LibraryService bookLibraryService = new BookLibraryService(dataHandler);

        UIFactory uiFactory = new UIFactory(bookLibraryService);

        UI ui = uiFactory.getUI(UIType.CLI);
        ui.show();
    }
}
