import java.util.List;

import Library.Database.Abstract.DataManagerFactory;
import Library.Database.Enums.SupportSqlDB;
import Library.Database.Interfaces.BookManager;
import Library.Database.Interfaces.DataManager;
import Library.Entity.Book;

public class Demo {

  private static void PrintTopic(String topic) {
    System.out.println("");
    System.out.println(topic);
    System.out.println("");
  }

  private static void PrintBookList(List<Book> books) {
    System.out.format("%-3s%-40s%-25s%-12s\n", "ID", "Title", "Author", "Availability");
    for (Book book : books) {
      System.out.format("%-3d%-40s%-25s%-12s\n", book.getId(), book.getTitle(), book.getAuthor(),
          book.getAvailability() ? "Available" : "Borrowed");
    }
  }

  public static void main(String[] args) {
    DataManager dataManager = new DataManagerFactory();

    BookManager bookManager = dataManager.GetBookManager(SupportSqlDB.SQLITE);

    PrintTopic("Creating Books");

    Book book1 = new Book("The Lord of the Rings", "J. R. R. Tolkien", "Fantasy");
    Book book2 = new Book("Rich Dad Poor Dad", "Robert Kiyosaki", "Finance");
    Book book3 = new Book("And then there were none", "Agatha Christie", "Mystery");
    Book book4 = new Book("The gay science", "Friedrich Nietzsche", "Philosophy");
    Book book5 = new Book("Harry Potter and the Philosopher Stone", "J. K. Rowling", "Fantasy");
    Book book6 = new Book("Metamorphosis", "Franz Kafka", "Fiction");

    bookManager.SaveBook(book1);
    bookManager.SaveBook(book2);
    bookManager.SaveBook(book3);
    bookManager.SaveBook(book4);
    bookManager.SaveBook(book5);
    bookManager.SaveBook(book6);

    PrintTopic("Getting All Books");
    PrintBookList(bookManager.GetAllBooks());
  }
}
