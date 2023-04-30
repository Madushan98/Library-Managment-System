package UI.GUI;

import Library.Entity.Book;
import Library.Entity.BookRecord;
import Library.Service.LibraryService;
import UI.Interfaces.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.List;

public class LibraryGUI implements UI {

    private final LibraryService libraryService;
    private JTable table;
    private JFrame frame;

    public LibraryGUI(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @Override
    public void show() {
        SwingUtilities.invokeLater(this::createAndShowGUI);
    }

    private void createAndShowGUI() {
        // create main frame
        frame = new JFrame("Library Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create table model
        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("ID");
        model.addColumn("Title");
        model.addColumn("Author");
        model.addColumn("Genre");
        model.addColumn("Available");

        // create table
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // add table to main frame
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // add buttons to control panel
        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> addBook());
        controlPanel.add(addButton);
        JButton removeButton = new JButton("Remove Book");
        removeButton.addActionListener(e -> removeBook());
        controlPanel.add(removeButton);
        JButton borrowButton = new JButton("Borrow Book");
        borrowButton.addActionListener(e -> borrowBook());
        controlPanel.add(borrowButton);
        JButton showOverDueBooksButton = new JButton("Overdue Books");
        showOverDueBooksButton.addActionListener(e -> showOverdueBookRecords());
        controlPanel.add(showOverDueBooksButton);
        JButton searchButton = new JButton("Search by Title");
        searchButton.addActionListener(e -> searchBookByTitle());
        controlPanel.add(searchButton);
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> refreshTable());
        controlPanel.add(refreshButton);
        frame.getContentPane().add(controlPanel, BorderLayout.NORTH);

        // show main frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // initial table data
        refreshTable();
    }

    private void addBook() {
        // create a dialog to input book information
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        Object[] inputFields = {
                "Title:", titleField,
                "Author:", authorField,
                "Genre:", genreField
        };
        int result = JOptionPane.showConfirmDialog(frame, inputFields, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String author = authorField.getText();
            String genre = genreField.getText();
            libraryService.addBook(title, author, genre);
            refreshTable();
        }
    }

    private void refreshTable() {
        List<Book> allBooks = libraryService.getAllBooks();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Book book : allBooks) {
            model.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getAvailability()});
        }
    }

    private void removeBook() {
        // get the selected book from the table
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a book to remove.");
            return;
        }
        int bookId = (int) table.getValueAt(selectedRow, 0);
        // confirm with the user before removing the book
        int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to remove this book?", "Remove Book",
                JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            libraryService.removeBook(bookId);
            refreshTable();
        }
    }

    private void borrowBook() {
        // get the selected book from the table
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(frame, "Please select a book to borrow.");
            return;
        }
        int bookId = (int) table.getValueAt(selectedRow, 0);

         boolean isAvailable = (boolean) table.getValueAt(selectedRow, 4);
        if (!isAvailable) {
            JOptionPane.showMessageDialog(frame, "Sorry, this book is not available.");
            return;
        }
        
        JTextField userField = new JTextField();
        JSpinner dateField = new JSpinner(new SpinnerDateModel());
        Object[] inputFields = {
                "User:", userField,
                "Borrow Date:", dateField
        };
        int result = JOptionPane.showConfirmDialog(frame, inputFields, "Borrow Book", JOptionPane.OK_CANCEL_OPTION);

        if (result == JOptionPane.OK_OPTION) {
            String borrower = userField.getText();
            Date borrowDate = ((SpinnerDateModel) dateField.getModel()).getDate();
            Instant instant = borrowDate.toInstant();
            ZoneId zoneId = ZoneId.systemDefault();
            ZonedDateTime zonedDateTime = instant.atZone(zoneId);
            LocalDate localDate = zonedDateTime.toLocalDate();

            // borrow the book
            Book bookBorrowed = libraryService.borrowBook(bookId, borrower, localDate);

            if (bookBorrowed != null) {
                JOptionPane.showMessageDialog(frame, "Book borrowed successfully!");
                refreshTable();
            } else {
                JOptionPane.showMessageDialog(frame, "Sorry, this book is not available for borrowing.");
            }
        }
    }

    private void showOverdueBookRecords() {
        JDialog dialog = new JDialog(frame, "Overdue Books", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        DefaultTableModel model = new DefaultTableModel();
        model.addColumn("Book ID");
        model.addColumn("Borrowed User");
        model.addColumn("Due Date");

        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        List<BookRecord> overdueBooks = libraryService.getOverdueBooks();

        for (BookRecord bookRecord : overdueBooks) {
            model.addRow(new Object[]{bookRecord.getBookId(), bookRecord.getUser(), bookRecord.getDueDate(),
            });
        }

        dialog.add(scrollPane);
        dialog.pack();
        dialog.setLocationRelativeTo(frame);
        dialog.setVisible(true);
    }

    private void searchBookByTitle() {
        String title = JOptionPane.showInputDialog(frame, "Enter the book title:");
        if (title != null && !title.isEmpty()) {
            List<Book> books = libraryService.searchBook(title);
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);
            for (Book book : books) {
                model.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthor(), book.getAvailability()});
            }
        }
    }
    
}