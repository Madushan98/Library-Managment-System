package Library.Entity;

import java.time.LocalDate;

public class BookRecord {
    private boolean overdue;

    private int bookId;

    private LocalDate dueDate;

    private String user;

    public BookRecord() {
        overdue = false;
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
