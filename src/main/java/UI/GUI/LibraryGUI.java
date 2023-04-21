package UI.GUI;

import Library.Entity.Book;
import Library.Service.LibraryService;
import UI.UI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class LibraryGUI implements UI {

    private final  LibraryService libraryService;
    private JTable table;
    private JFrame frame;
    public  LibraryGUI(LibraryService libraryService){
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
        model.addColumn("Available");

        // create table
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // add table to main frame
        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // add buttons to control panel
        JPanel controlPanel = new JPanel(new FlowLayout());
        JButton addButton = new JButton("Add Book");
        addButton.addActionListener(e -> addBookUI());
        controlPanel.add(addButton);
        JButton removeButton = new JButton("Remove Book");
        removeButton.addActionListener(e -> removeBook());
        controlPanel.add(removeButton);
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

    private void addBookUI() {
        // create a dialog to input book information
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        Object[] inputFields = {
                "Title:", titleField,
                "Author:", authorField
        };
        int result = JOptionPane.showConfirmDialog(frame, inputFields, "Add Book", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String title = titleField.getText();
            String author = authorField.getText();
            libraryService.addBook(title,author);
            refreshTable();
        }
    }

    private void refreshTable() {
        List<Book> allBooks = libraryService.getAllBooks();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);
        for (Book book : allBooks) {
            model.addRow(new Object[] { book.getId(), book.getTitle(), book.getAuthor(), book.isAvailability() });
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
        int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to remove this book?", "Remove Book", JOptionPane.YES_NO_OPTION);
        if (result == JOptionPane.YES_OPTION) {
            libraryService.removeBook(bookId);
            refreshTable();
        }
    }

}
