package Library.Entity;

import java.time.LocalDate;

public class BookRecord {
    private int id;
    private int bookId;
    private String user;
    private boolean returned;
    private LocalDate dueDate;

    public BookRecord(int id, int bookId, LocalDate dueDate, boolean returned, String user) {
        this.id = id;
        this.bookId = bookId;
        this.dueDate = dueDate;
        this.returned = returned;
        this.user = user;
    }

    public BookRecord(int id, int bookId, String user, boolean returned, String dueDate) {
        this.id = id;
        this.bookId = bookId;
        this.returned = returned;
        this.user = user;
        this.dueDate = LocalDate.parse(dueDate);
    }

    public BookRecord(int bookId, String user) {
        this.id = -1;
        this.bookId = bookId;
        this.user = user;
        this.returned = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
