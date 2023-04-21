package Library.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import Library.Database.Interfaces.SqlDatabase;
import Library.Entity.Book;

public class Sqlite implements SqlDatabase {

    private static Sqlite instance = null;
    private static final String DB_NAME = "library.sqlite";
    private static Connection conn = null;
    private static Logger logger = Logger.getLogger(Sqlite.class.getName());

    private Sqlite() {
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);

            logger.info("Opened database successfully");
        } catch (Exception e) {
            logger.severe("Failed to open database");

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS BOOKS " +
                    "(ID        INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " TITLE     TEXT    NOT NULL, " +
                    " AUTHOR    TEXT    NOT NULL, " +
                    " AVAILABLE INT     DEFAULT 1)";

            stmt.executeUpdate(sql);
            stmt.close();

            logger.info("Created books table successfully");
        } catch (Exception e) {
            logger.severe(DB_NAME + ": Failed to create table books");

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }

        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS RECORDS " +
                    "(ID        INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " BOOK_ID   INT     NOT NULL, " +
                    " USER_ID   TEXT     NOT NULL, " +
                    " DATE      TEXT    NOT NULL)";

            stmt.executeUpdate(sql);
            stmt.close();

            logger.info("Created records table successfully");
        } catch (Exception e) {
            logger.severe(DB_NAME + ": Failed to create table records");

            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    /**
     * Get the instance of the database
     */
    public static Sqlite getInstance() {
        if (instance == null) {
            instance = new Sqlite();
        }
        return instance;
    }

    /**
     * Execute a update query
     * 
     * @param sqlString The query to execute
     */
    public void ExecuteUpdate(String sqlString) {
        if (conn == null) {
            throw new RuntimeException("Connection is null");
        }

        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sqlString);
            stmt.close();
        } catch (Exception e) {
            logger.severe(
                    "Failed to execute query: " + sqlString + ". " + e.getMessage());
        }
    }

    /**
     * Execute a query
     * 
     * @param sqlString The query to execute
     * @return The result set
     */
    public ResultSet ExecuteQuery(String sqlString) {
        if (conn == null) {
            throw new RuntimeException("Connection is null");
        }

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlString);
            stmt.close();

            return rs;
        } catch (Exception e) {
            logger.severe(
                    "Failed to execute query: " + sqlString + ". " + e.getMessage());
        }
        return null;
    }

    public List<Book> ExecuteBookQuery(String sqlString) {
        if (conn == null) {
            throw new RuntimeException("Connection is null");
        }

        List<Book> books = new ArrayList<Book>();

        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sqlString);

            while (rs.next()) {
                books.add(new Book(rs.getInt("ID"), rs.getString("TITLE"), rs.getString("AUTHOR"),
                        rs.getInt("AVAILABLE") == 1));
            }

            rs.close();
            stmt.close();

        } catch (Exception e) {
            logger.severe(
                    "Failed to execute query: " + sqlString + ". " + e.getMessage());
        }
        return books;
    }
}
