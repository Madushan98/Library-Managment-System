import Enums.UIType;
import Library.Database.Abstract.DataManagerFactory;
import Library.Database.Enums.SupportSqlDB;
import Library.Database.Interfaces.DataManager;
import Library.Service.BookLibraryService;
import Library.Service.LibraryService;
import UI.Interfaces.UI;
import UI.UIFactory.UIFactory;

public class LMS {

    public static void main(String[] args) {
        DataManager dataManager = new DataManagerFactory();

        LibraryService bookLibraryService = new BookLibraryService(dataManager.GetBookManager(SupportSqlDB.SQLITE));

        UIFactory uiFactory = new UIFactory(bookLibraryService);

        UI ui = uiFactory.getUI(UIType.GUI);
        ui.show();
    }
}
