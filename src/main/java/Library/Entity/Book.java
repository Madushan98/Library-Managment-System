package Library.Entity;

import Library.Database.Interfaces.BookManager;
import Library.Database.Interfaces.RecordManager;

public class Book {

    private int id;
    private String title;
    private String author;
    private boolean availability;

    public Book(String title, String author) {
        this.id = -1;
        this.title = title;
        this.author = author;
        this.availability = true;
    }

    public Book(int id, String title, String author, boolean availability) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.availability = availability;
    }

    public Book(int id, BookManager bookManager) {
        Book book = bookManager.GetBook(id);

        if (book == null) {
            throw new RuntimeException("Book not found");
        }

        this.id = book.getId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.availability = book.getAvailability();
    }

    public Book Delete(BookManager bookManager) {
        bookManager.DeleteBook(id);
        this.availability = false;
        return this;
    }

    public Book Save(BookManager bookManager) {
        if (this.id == -1) {
            this.id = bookManager.CreateBook(this);
            return this;
        }

        bookManager.UpdateBook(this);
        return this;
    }

    public BookRecord BorrowBook(BookManager bookManager, RecordManager recordManager) {
        if (!this.availability) {
            System.out.println("Book is not available");
            return null;
        }

        BookRecord bookRecord = new BookRecord(this.id, "user");
        recordManager.CreateBookRecord(bookRecord);
        this.availability = false;
        this.Save(bookManager);

        return bookRecord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean getAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }
}
