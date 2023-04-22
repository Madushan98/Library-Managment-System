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
        DataManager dataManager = new DataManagerFactory();
        BookManager bookManager = dataManager.GetBookManager(SupportSqlDB.SQLITE);
        RecordManager recordManager = dataManager.GetRecordManager(SupportSqlDB.SQLITE);

        LibraryService bookLibraryService = new BookLibraryService(bookManager, recordManager);

        UIFactory uiFactory = new UIFactory(bookLibraryService);

        UI ui = uiFactory.getUI(UIType.CLI);
        ui.show();
    }
}
