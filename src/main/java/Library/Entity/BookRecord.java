package Library.Entity;

import java.time.LocalDate;

public class BookRecord {
    private boolean overdue;
    private int bookId;
    private LocalDate dueDate;
    private String user;

    public BookRecord(int bookId, LocalDate dueDate, boolean overdue, String user ) {
        this.bookId = bookId;
        this.dueDate = dueDate;
        this.overdue = overdue;
        this.user = user;
    }

    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
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
