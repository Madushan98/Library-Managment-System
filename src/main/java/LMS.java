import java.util.List;

import Enums.UIType;
import Library.Database.Adapters.Sql;
import Enums.SupportSqlDB;
import Library.Database.Interfaces.DataHandler;
import Library.Entity.Book;
import Library.Entity.BookFactory;
import Library.Service.BookLibraryService;
import Library.Service.LibraryService;
import UI.UI;
import UI.UIFactory.UIFactory;

public class LMS {

    public static void main(String[] args) {
        DataHandler dataHandler = new Sql(SupportSqlDB.SQLITE);

        System.out.println("Create books");

        dataHandler.CreateBook(BookFactory.createBook("The Lord of the Rings", "J.R.R. Tolkien"));
        dataHandler.CreateBook(BookFactory.createBook("The Hobbit", "J.R.R. Tolkien"));
        dataHandler.CreateBook(BookFactory.createBook("The Silmarillion", "J.R.R. Tolkien"));
        dataHandler.CreateBook(BookFactory.createBook("The Fellowship of the Ring", "J.R.R. Tolkien"));

        LibraryService bookLibraryService = new BookLibraryService(dataHandler);

        UIFactory uiFactory = new UIFactory(bookLibraryService);

        UI ui = uiFactory.getUI(UIType.GUI);
        ui.show();
    }
}
