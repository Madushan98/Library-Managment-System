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
    Book lotr = new Book("The Lord of the Rings", "J. R. R. Tolkien");
    Book hp1 = new Book("Harry Potter and the Philosopher Stone", "J. K. Rowling");
    Book lp = new Book("The Little Prince", "Antoine de Saint-Exupéry");
    Book atwn = new Book("And Then There Were None", "Agatha Christie");
    new Book("The Da Vinci Code", "Dan Brown").Save(bookManager);
    new Book("The Alchemist", "Paulo Coelho").Save(bookManager);
    new Book("Dream of the Red Chamber", "Cao Xueqin").Save(bookManager);

    lotr = lotr.Save(bookManager);
    hp1 = hp1.Save(bookManager);
    lp = lp.Save(bookManager);
    atwn = atwn.Save(bookManager);

    PrintTopic("Getting All Books");
    PrintBookList(bookManager.GetAllBooks());

    PrintTopic("Removing Book with ID 1");
    bookManager.DeleteBook(1);
    // or Book.Delete(bookManager);
    PrintBookList(bookManager.GetAllBooks());

    PrintTopic("Remove And Then There Were None");
    atwn.Delete(bookManager);
    PrintBookList(bookManager.GetAllBooks());

    PrintTopic("Edit Book with ID 5");
    Book bookToBeEdit = bookManager.GetBook(5);
    bookToBeEdit.setTitle("Harry Potter and the Sorcerer Stone"); // hp1.setTitle("Harry Potter and the Sorcerer Stone")
                                                                  // also works!!
    bookToBeEdit.Save(bookManager);
    PrintBookList(bookManager.GetAllBooks());

    PrintTopic("Searching for Books with Title containing \"Stone\"");
    PrintBookList(bookManager.SearchByName("Stone"));

    PrintTopic("Getting All Available Books");
    PrintBookList(bookManager.GetAllAvailableBooks());

    PrintTopic("Borrowing Book with ID 3");
    Book bookToBeBorrowed = new Book(3, bookManager);
    bookToBeBorrowed.BorrowBook(bookManager, recordManager);
    PrintBookList(bookManager.GetAllBooks());

    PrintTopic("Getting All Book Records");
    PrintRecordList(recordManager.GetAllBookRecords());

    PrintTopic("Return Borrowed Book");
    Book bookToBeReturned = new Book(3, bookManager);
    bookToBeReturned.ReturnBook(bookManager, recordManager);
    PrintBookList(bookManager.GetAllBooks());

  }
}
