import java.util.List;

import Library.Database.Abstract.DataManagerFactory;
import Library.Database.Enums.SupportSqlDB;
import Library.Database.Interfaces.BookManager;
import Library.Database.Interfaces.DataManager;
import Library.Database.Interfaces.RecordManager;
import Library.Entity.Book;
import Library.Entity.BookRecord;

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

  private static void PrintRecordList(List<BookRecord> records) {
    System.out.format("%-3s%-7s%-20s%-10s%-10s\n", "ID", "BookID", "User", "Returned", "Due Date");
    for (BookRecord record : records) {
      System.out.format("%-3d%-7d%-20s%-10s%-10s\n", record.getId(), record.getBookId(), record.getUser(),
          record.isReturned() ? "Yes" : "No", record.getDueDate());
    }
  }

  public static void main(String[] args) {
    DataManager dataManager = new DataManagerFactory();

    BookManager bookManager = dataManager.GetBookManager(SupportSqlDB.SQLITE);
    RecordManager recordManager = dataManager.GetRecordManager(SupportSqlDB.SQLITE);

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

    PrintTopic("Removing Book with ID 1");
    bookManager.DeleteBook(1);
    PrintBookList(bookManager.GetAllBooks());

    PrintTopic("Edit Book with ID 5");
    Book bookToBeEdit = bookManager.GetBook(5);
    bookToBeEdit.setTitle("Harry Potter and the Sorcerer Stone");
    bookToBeEdit = bookManager.UpdateBook(bookToBeEdit);
    PrintBookList(bookManager.GetAllBooks());

    PrintTopic("Searching for Books with Title containing \"Stone\"");
    PrintBookList(bookManager.SearchByName("Stone"));

    PrintTopic("Getting All Available Books");
    PrintBookList(bookManager.GetAllAvailableBooks());

    PrintTopic("Borrowing Book with ID 3");
    Book bookToBeBorrowed = bookManager.GetBook(3);
    BookRecord borrowRecord = new BookRecord(bookToBeBorrowed.getId(), "user");
    borrowRecord = recordManager.CreateBookRecord(borrowRecord);
    bookToBeBorrowed.setAvailability(false);
    bookToBeBorrowed = bookManager.UpdateBook(bookToBeBorrowed);
    PrintBookList(bookManager.GetAllBooks());

    PrintTopic("Getting All Book Records");
    PrintRecordList(recordManager.GetAllBookRecords());

    PrintTopic("Return Borrowed Book");
    Book bookToBeReturned = bookManager.GetBook(3);
    BookRecord returnRecord = recordManager.GetLastBookRecordForBook(bookToBeReturned.getId());
    returnRecord.setReturned(true);
    recordManager.UpdateBookRecord(returnRecord);
    bookToBeReturned.setAvailability(true);
    bookToBeReturned = bookManager.UpdateBook(bookToBeReturned);
    PrintBookList(bookManager.GetAllBooks());

    PrintTopic("Getting All Book Records");
    PrintRecordList(recordManager.GetAllBookRecords());
  }
}
