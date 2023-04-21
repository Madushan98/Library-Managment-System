import java.util.List;

import Library.Database.Adapters.Sql;
import Library.Database.Enums.SupportSqlDB;
import Library.Database.Interfaces.DataHandler;
import Library.Entity.Book;

public class LMS {

    public static void main(String[] args) {
        DataHandler dataHandler = new Sql(SupportSqlDB.SQLITE);

        System.out.println("Create books");

        dataHandler.CreateBook(new Book("The Lord of the Rings", "J.R.R. Tolkien"));
        dataHandler.CreateBook(new Book("The Hobbit", "J.R.R. Tolkien"));
        dataHandler.CreateBook(new Book("The Silmarillion", "J.R.R. Tolkien"));
        dataHandler.CreateBook(new Book("The Fellowship of the Ring", "J.R.R. Tolkien"));

        System.out.println("Get book with id 1");

        Book book = dataHandler.GetBook(1);
        System.out.println(book.getTitle());

        System.out.println("Get all books");

        List<Book> books = dataHandler.GetAllBooks();
        for (Book b : books) {
            System.out.println(b.getTitle());
        }

        System.out.println("Delete book with id 2");
        dataHandler.DeleteBook(2);

        System.out.println("Get all available books");
        dataHandler.UpdateBook(new Book(1, "The Lord of the Rings", "J.R.R. Tolkien",
                false));

        books = dataHandler.GetAllAvailableBooks();
        for (Book b : books) {
            System.out.println(b.getTitle());
        }
    }
}
